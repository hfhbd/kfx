package io.github.hfhbd.kfx

import app.softwork.serviceloader.*
import com.squareup.kotlinpoet.*
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenerator
import java.nio.file.*
import kotlin.collections.iterator

@ServiceLoader(CodeGenerator::class)
class KotlinCodeGenerator : KotlinPoetCodeGenerator {
    override fun generate(codeGenTree: CodeGenTree, outputFolder: Path) {
        val files = generateFileSpec(codeGenTree)
        for (file in files) {
            file.writeTo(outputFolder)
        }
    }

    override fun generateFileSpec(codeGenTree: CodeGenTree): List<FileSpec> {
        val fileSpecs = mutableListOf<FileSpec>()
        for (klass in codeGenTree.classes) {
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
            val type = typeSpec.addMember(member, isFault, addInitializer = true)

            constructor.addParameter(
                ParameterSpec.builder(member.name, type).apply {
                    for (annotation in member.annotations) {
                        addAnnotation(annotation.toAnno())
                    }
                }.apply {
                    if (member.nullable) {
                        when (member.type) {
                            is CodeGenTree.Type.LIST -> defaultValue(
                                CodeBlock.of(
                                    "%M()",
                                    MemberName("kotlin.collections", "emptyList", isExtension = true),
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

    private fun TypeSpec.Builder.addMember(
        member: CodeGenTree.Member,
        isFault: Boolean,
        addInitializer: Boolean,
    ): TypeName {
        val type = member.type.toPoetType().copy(
            nullable = if (member.type is CodeGenTree.Type.LIST) false else member.nullable,
        )

        val prop = PropertySpec.builder(name = member.name, type = type)
            .mutable(member.mutable)

        if (addInitializer) {
            prop.initializer(member.name)
        }

        if (member.overrideable || isFault && member.name == "message") {
            prop.addModifiers(KModifier.PUBLIC, KModifier.OVERRIDE)
        }

        val doc = member.documentation
        if (doc != null && doc.isNotBlank()) {
            prop.addKdoc(doc.toKdoc())
        }

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
                            addMember(member, isFault, addInitializer = false)
                        }
                    }
            }

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
        if (documentation != null && documentation.isNotBlank()) {
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
                    if (documentation != null && documentation.isNotBlank()) {
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
