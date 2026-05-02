package io.github.hfhbd.kfx.kotlin

import com.squareup.kotlinpoet.ARRAY
import com.squareup.kotlinpoet.BOOLEAN
import com.squareup.kotlinpoet.BYTE
import com.squareup.kotlinpoet.BYTE_ARRAY
import com.squareup.kotlinpoet.CHAR
import com.squareup.kotlinpoet.CHAR_ARRAY
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.DOUBLE
import com.squareup.kotlinpoet.FLOAT
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.INT
import com.squareup.kotlinpoet.LIST
import com.squareup.kotlinpoet.LONG
import com.squareup.kotlinpoet.MAP
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.NameAllocator
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.SHORT
import com.squareup.kotlinpoet.STAR
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeVariableName
import com.squareup.kotlinpoet.UNIT
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenerator

interface KotlinPoetCodeGenerator : CodeGenerator {
    fun generateFileSpec(codeGenTree: CodeGenTree): List<FileSpec>
}

fun CodeGenTree.Expression.ConstExpression.toCodeBlock(): CodeBlock = when (this) {
    is CodeGenTree.Expression.StringLiteral -> CodeBlock.of("%S", value)

    is CodeGenTree.Expression.IntLiteral -> CodeBlock.of("%L", value)

    is CodeGenTree.Expression.LongLiteral -> CodeBlock.of("%L", value)

    is CodeGenTree.Expression.FloatLiteral -> CodeBlock.of("%L", value)

    is CodeGenTree.Expression.DoubleLiteral -> CodeBlock.of("%L", value)

    is CodeGenTree.Expression.BooleanLiteral -> CodeBlock.of("%L", value)

    is CodeGenTree.Expression.ClassLiteral -> CodeBlock.of("%T::class", value.toKotlinPoet())

    is CodeGenTree.Expression.EnumLiteral -> CodeBlock.of(
        "%M",
        MemberName(qualifiedName.toKotlinPoet() as ClassName, member),
    )

    is CodeGenTree.Expression.ByteLiteral -> CodeBlock.of("%L", value)

    is CodeGenTree.Expression.CharLiteral -> CodeBlock.of("'%L'", value)

    is CodeGenTree.Expression.ShortLiteral -> CodeBlock.of("%L", value)
}

fun CodeGenTree.Expression.toCodeBlock(
    nameAllocator: NameAllocator,
): CodeBlock = when (this) {
    is CodeGenTree.Expression.ConstExpression -> toCodeBlock()

    is CodeGenTree.Expression.UuidLiteral -> CodeBlock.of(
        "%T.parse(%S).",
        ClassName("kotlin.uuid", "Uuid"),
        value.toString(),
    )

    is CodeGenTree.Expression.DurationLiteral -> CodeBlock.of(
        "%T.parse(%S)",
        ClassName("kotlin.time", "Duration"),
        value.toString(),
    )

    is CodeGenTree.Expression.DateLiteral -> CodeBlock.of(
        "%T.parse(%S)",
        ClassName("kotlinx.datetime", "LocalDate"),
        value.toString(),
    )

    is CodeGenTree.Expression.InstantLiteral -> CodeBlock.of(
        "%T.parse(%S)",
        ClassName("kotlin.time", "Instant"),
        value.toString(),
    )

    is CodeGenTree.Expression.CallMember -> CodeBlock.of("%N", member.name)

    is CodeGenTree.Expression.Create -> {
        val block = CodeBlock.builder()
        block.add("%T(", normalClass.toPoetType())
        if (parameters.isNotEmpty()) {
            block.add("\n")
            block.indent()
        }
        for ((name, parameter) in parameters) {
            block.add("$name = %L,\n", parameter.toCodeBlock(nameAllocator))
        }
        if (parameters.isNotEmpty()) {
            block.unindent()
        }
        block.add(")")
        block.build()
    }

    is CodeGenTree.Expression.Parameter -> CodeBlock.of("%L", nameAllocator[parameter.name])

    is CodeGenTree.Expression.Input -> CodeBlock.of("input")

    is CodeGenTree.Expression.Output -> CodeBlock.of("output")

    is CodeGenTree.Expression.Response -> CodeBlock.of("response")

    is CodeGenTree.Expression.Chain -> CodeBlock.of(
        "%L.%L",
        lhs.toCodeBlock(nameAllocator),
        rhs.toCodeBlock(nameAllocator),
    )

    is CodeGenTree.Expression.Plus -> CodeBlock.of(
        "%L + %L",
        lhs.toCodeBlock(nameAllocator),
        rhs.toCodeBlock(nameAllocator),
    )

    CodeGenTree.Expression.NullLiteral -> CodeBlock.of("null")

    is CodeGenTree.Expression.ListOf -> CodeBlock.Builder().apply {
        add("%M(", MemberName("kotlin.collections", "listOf", isExtension = true))
        for (expr in expressions) {
            add(expr.toCodeBlock(nameAllocator))
            add("\n,")
        }
        add(")")
    }.build()

    is CodeGenTree.Expression.ArrayOf -> CodeBlock.Builder().apply {
        add("%M(", MemberName("kotlin", "arrayOf", isExtension = true))
        for (expr in expressions) {
            add(expr.toCodeBlock(nameAllocator))
            add("\n,")
        }
        add(")")
    }.build()

    is CodeGenTree.Expression.ConstArrayOf -> CodeBlock.Builder().apply {
        add("%M(", MemberName("kotlin", "arrayOf", isExtension = true))
        for (expr in expressions) {
            add(expr.toCodeBlock())
            add(",\n")
        }
        add(")")
    }.build()

    is CodeGenTree.Expression.CallStatic -> CodeBlock.of("%T", type.toPoetType())

    is CodeGenTree.Expression.CallFunction -> CodeBlock.Builder().apply {
        if (function.packageName != null) {
            add("%M", MemberName(function.packageName!!, function.name, isExtension = true))
        } else {
            add(function.name)
        }
        add("(")
        for ((parameterName, parameter) in parameters) {
            add("%L = %L", parameterName, parameter.toCodeBlock(nameAllocator))
            add(",\n")
        }
        add(")")
    }.build()

    is CodeGenTree.Expression.Is -> CodeBlock.of("is %T", type.toPoetType())
}

fun String.toKdoc() = CodeBlock.of("%L", replace("*/*", "* / *").replace("/*", "/ *").replace("*/", "* /"))

fun CodeGenTree.Type.toPoetType(includeGenerics: Boolean = true): TypeName = when (this) {
    is CodeGenTree.Type.Builtin -> toPoetType()

    is CodeGenTree.NormalClass -> if (includeGenerics && types.isNotEmpty()) {
        ClassName(packageName, names).parameterizedBy(
            types.map {
                it.toPoetType(includeGenerics = includeGenerics)
            },
        )
    } else {
        ClassName(packageName, names)
    }

    is CodeGenTree.Enum -> ClassName(packageName, names)

    is CodeGenTree.Type.LIST -> if (includeGenerics) {
        LIST.parameterizedBy(item.toPoetType())
    } else {
        LIST
    }

    is CodeGenTree.Type.MAP -> if (includeGenerics) {
        MAP.parameterizedBy(key.toPoetType(), value.toPoetType())
    } else {
        MAP
    }

    is CodeGenTree.Type.ARRAY -> if (includeGenerics) {
        ARRAY.parameterizedBy(item.toPoetType())
    } else {
        ARRAY
    }

    is CodeGenTree.Type.Parameter -> TypeVariableName(
        name,
        upperBound.map {
            it.toPoetType(includeGenerics = includeGenerics)
        },
    )

    CodeGenTree.Type.STAR -> STAR

    CodeGenTree.Type.Unknown -> error(
        "Format specific type needs to be transformed first by adding a format transformer.",
    )
}

fun CodeGenTree.Type.Builtin.toPoetType(): ClassName = when (this) {
    CodeGenTree.Type.Builtin.UNIT -> UNIT
    CodeGenTree.Type.Builtin.INT -> INT
    CodeGenTree.Type.Builtin.LONG -> LONG
    CodeGenTree.Type.Builtin.BOOLEAN -> BOOLEAN
    CodeGenTree.Type.Builtin.DOUBLE -> DOUBLE
    CodeGenTree.Type.Builtin.STRING -> STRING
    CodeGenTree.Type.DateType.DATE -> ClassName("kotlinx.datetime", "LocalDate")
    CodeGenTree.Type.DateType.INSTANT -> ClassName("kotlin.time", "Instant")
    CodeGenTree.Type.Builtin.FILE -> error("This class needs special support")
    CodeGenTree.Type.Builtin.BYTEARRAY -> BYTE_ARRAY
    CodeGenTree.Type.Builtin.BYTESTRING -> STRING
    CodeGenTree.Type.Builtin.FLOAT -> FLOAT
    CodeGenTree.Type.Builtin.DURATION -> ClassName("kotlin.time", "Duration")
    CodeGenTree.Type.Builtin.UUID -> ClassName("kotlin.uuid", "Uuid")
    CodeGenTree.Type.Builtin.CHARARRAY -> CHAR_ARRAY
    CodeGenTree.Type.Builtin.BYTE -> BYTE
    CodeGenTree.Type.Builtin.CHAR -> CHAR
    CodeGenTree.Type.Builtin.SHORT -> SHORT
}

fun CodeGenTree.ClassName.toKotlinPoet(): TypeName = ClassName(packageName, names).let {
    if (runtimeTypes.isNotEmpty()) {
        it.parameterizedBy(runtimeTypes.map { it.toPoetType() })
    } else {
        it
    }
}
