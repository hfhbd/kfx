package io.github.hfhbd.kfx.kotlin.classes

import app.softwork.serviceloader.ServiceLoader
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.TypeVariableName
import com.squareup.kotlinpoet.joinToCode
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenerator
import io.github.hfhbd.kfx.kotlin.KotlinPoetCodeGenerator
import io.github.hfhbd.kfx.kotlin.toCodeBlock
import io.github.hfhbd.kfx.kotlin.toKdoc
import io.github.hfhbd.kfx.kotlin.toKotlinPoet
import io.github.hfhbd.kfx.kotlin.toPoetType
import java.nio.file.Path
import kotlin.collections.iterator

@ServiceLoader(CodeGenerator::class)
class KotlinClassesGenerator : KotlinPoetCodeGenerator {
    override fun generate(codeGenTree: CodeGenTree, outputDirectory: Path) {
        val files = generateFileSpec(codeGenTree)
        for (file in files) {
            file.writeTo(outputDirectory)
        }
    }

    override fun generateFileSpec(codeGenTree: CodeGenTree): List<FileSpec> {
        val fileSpecs = mutableListOf<FileSpec>()
        for (klass in codeGenTree.classes.filter {
            !it.provided
        }) {
            when (klass) {
                is CodeGenTree.Enum -> fileSpecs.add(klass.generateFile())

                is CodeGenTree.NormalClass -> {
                    fileSpecs.add(klass.generateFile())
                }
            }
        }
        return fileSpecs
    }

    private fun CodeGenTree.NormalClass.generateNormalClass(): TypeSpec.Builder {
        val typeSpec = TypeSpec.classBuilder(names.single())
        typeSpec.addModifiers(KModifier.DATA)

        for (typeParameter in types) {
            require(typeParameter is CodeGenTree.Type.Parameter)
            typeSpec.addTypeVariable(
                TypeVariableName(
                    name = typeParameter.name,
                    bounds = typeParameter.upperBound.map { it.toPoetType() },
                ),
            )
        }
        val constructor = FunSpec.constructorBuilder()

        for (member in members) {
            val type = typeSpec.addMember(member, isFault) {
                initializer(member.name)
            }

            constructor.addParameter(
                ParameterSpec.builder(member.name, type).apply {
                    for (annotation in member.annotations) {
                        addAnnotation(annotation.toAnno())
                    }
                    if (member.nullable) {
                        when (member.type) {
                            is CodeGenTree.Type.LIST -> defaultValue(
                                CodeBlock.of(
                                    "%M()",
                                    MemberName("kotlin.collections", "emptyList", isExtension = true),
                                ),
                            )

                            is CodeGenTree.Type.MAP -> defaultValue(
                                CodeBlock.of(
                                    "%M()",
                                    MemberName("kotlin.collections", "emptyMap", isExtension = true),
                                ),
                            )

                            else -> defaultValue("null")
                        }
                    }
                }.build(),
            )
        }

        typeSpec.primaryConstructor(constructor.build())

        return typeSpec
    }

    private fun CodeGenTree.NormalClass.generateValueClass(): TypeSpec.Builder {
        val typeSpec = TypeSpec.classBuilder(names.single())
        typeSpec.addAnnotation(AnnotationSpec.builder(ClassName("kotlin.jvm", "JvmInline")).build())
        typeSpec.addModifiers(KModifier.VALUE)

        for (typeParameter in types) {
            require(typeParameter is CodeGenTree.Type.Parameter)
            typeSpec.addTypeVariable(
                TypeVariableName(
                    name = typeParameter.name,
                    bounds = typeParameter.upperBound.map { it.toPoetType() },
                ),
            )
        }

        val valueMember = members.single()
        val valueMemberType = valueMember.type.toPoetType()

        val privateConstructor = FunSpec.constructorBuilder()
        privateConstructor.addModifiers(KModifier.PRIVATE)
        privateConstructor.addParameter(
            name = valueMember.name,
            type = valueMemberType,
        )
        typeSpec.primaryConstructor(privateConstructor.build())
        typeSpec.addMember(valueMember, isFault = false) {
            addModifiers(KModifier.PRIVATE)
            initializer(valueMember.name)
        }

        val constructor = FunSpec.constructorBuilder()

        for (member in computedProperties) {
            val type = typeSpec.addMember(member, isFault = false) {
                getter(
                    FunSpec.getterBuilder().addStatement(
                        "return ${valueMember.name}.${member.name}",
                    ).build(),
                )
            }

            constructor.addParameter(
                ParameterSpec.builder(member.name, type).apply {
                    for (annotation in member.annotations) {
                        addAnnotation(annotation.toAnno())
                    }
                    if (member.nullable) {
                        when (member.type) {
                            is CodeGenTree.Type.LIST -> defaultValue(
                                CodeBlock.of(
                                    "%M()",
                                    MemberName("kotlin.collections", "emptyList", isExtension = true),
                                ),
                            )

                            is CodeGenTree.Type.MAP -> defaultValue(
                                CodeBlock.of(
                                    "%M()",
                                    MemberName("kotlin.collections", "emptyMap", isExtension = true),
                                ),
                            )

                            else -> defaultValue("null")
                        }
                    }
                }.build(),
            )
        }

        typeSpec.addFunction(
            constructor
                .callThisConstructor(
                    CodeBlock.of(
                        "%T%L%L%L",
                        valueMemberType,
                        if (computedProperties.isEmpty()) "" else "(",
                        computedProperties.map {
                            CodeBlock.of(it.name)
                        }.joinToCode(),
                        if (computedProperties.isEmpty()) "" else ")",
                    ),
                )
                .build(),
        )

        return typeSpec
    }

    private fun TypeSpec.Builder.addMember(
        member: CodeGenTree.Member,
        isFault: Boolean,
        custom: PropertySpec.Builder.() -> Unit = {},
    ): TypeName {
        val type = member.type.toPoetType().copy(
            nullable = if (member.type is CodeGenTree.Type.LIST ||
                member.type is CodeGenTree.Type.MAP
            ) {
                false
            } else {
                member.nullable
            },
        )

        val prop = PropertySpec.builder(name = member.name, type = type)
            .mutable(member.mutable)

        if (member.overrideable || isFault && member.name == "message") {
            prop.addModifiers(KModifier.PUBLIC, KModifier.OVERRIDE)
        }

        val doc = member.documentation
        if (!doc.isNullOrBlank()) {
            prop.addKdoc(doc.toKdoc())
        }

        prop.custom()

        addProperty(prop.build())

        return type
    }

    private fun CodeGenTree.Annotation.toAnno(): AnnotationSpec {
        val anno = AnnotationSpec.builder(
            ClassName(
                packageName,
                names,
            ),
        )
        for ((name, value) in values) {
            anno.addMember("%L = %L", name, value.toCodeBlock())
        }
        return anno.build()
    }

    private fun CodeGenTree.NormalClass.generateFile(): FileSpec {
        val fileSpec = FileSpec.builder(packageName, names.single())
        fileSpec.addType(generate())
        return fileSpec.build()
    }

    private fun CodeGenTree.NormalClass.generate(): TypeSpec {
        val typeBuilder = when {
            members.isEmpty() && !isSealed -> {
                TypeSpec.objectBuilder(names.single())
                    .addModifiers(KModifier.DATA)
            }

            isSealed -> {
                TypeSpec.interfaceBuilder(names.single())
                    .addModifiers(KModifier.SEALED)
                    .apply {
                        for (member in members) {
                            addMember(member, isFault)
                        }
                    }
            }

            isValue -> generateValueClass()

            else -> {
                generateNormalClass()
            }
        }

        if (isFault) {
            typeBuilder.superclass(ClassName("kotlin", "Exception"))
        }

        if (superClassName != null) {
            typeBuilder.addSuperinterface(superClassName!!.toKotlinPoet())
        }
        for (superInterface in superInterfaces) {
            typeBuilder.addSuperinterface(superInterface.toKotlinPoet())
        }

        val documentation = documentation
        if (!documentation.isNullOrBlank()) {
            typeBuilder.addKdoc(documentation.toKdoc())
        }

        for (annotation in annotations) {
            typeBuilder.addAnnotation(annotation.toAnno())
        }

        for (inner in innerClasses) {
            when (inner) {
                is CodeGenTree.Enum -> typeBuilder.addType(inner.generate())
                is CodeGenTree.NormalClass -> typeBuilder.addType(inner.generate())
            }
        }
        return typeBuilder.build()
    }

    private fun CodeGenTree.Enum.generateFile(): FileSpec {
        val fileSpec = FileSpec.builder(packageName, names.single())
        fileSpec.addType(generate())
        return fileSpec.build()
    }

    private fun CodeGenTree.Enum.generate(): TypeSpec {
        val typeSpec = TypeSpec.enumBuilder(names.single())
        for (annotation in annotations) {
            typeSpec.addAnnotation(annotation.toAnno())
        }
        for (value in values) {
            typeSpec.addEnumConstant(
                value.name,
                TypeSpec.anonymousClassBuilder().apply {
                    for (annotation in value.annotations) {
                        addAnnotation(annotation.toAnno())
                    }
                    val documentation = value.documentation
                    if (!documentation.isNullOrBlank()) {
                        addKdoc(documentation.toKdoc())
                    }
                }
                    .build(),
            )
        }
        typeSpec.addFunction(
            FunSpec.builder("toString")
                .returns(STRING)
                .addModifiers(KModifier.OVERRIDE)
                .addCode(
                    "return serializer().descriptor.getElementName(ordinal)",
                )
                .build(),
        )
        return typeSpec.build()
    }
}
