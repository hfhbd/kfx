package io.github.hfhbd.kfx

import io.github.hfhbd.kfx.codegen.CodeGenCreator
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenTree.Expression.*
import io.github.hfhbd.kfx.codegen.CodeGenTree.Type.*
import io.github.hfhbd.kfx.ir.IRTree

interface KotlinxCoreCreator : CodeGenCreator {
    override fun toCodeGen(ir: IRTree.Member, name: String): CodeGenTree.Member = CodeGenTree.Member(
        name = name,
        type = toCodeGen(ir.type),
        nullable = ir.nullable,
        documentation = ir.documentation,
        annotations = if (
            ir.serialName != null
        ) {
            listOf(serialName(ir.serialName!!))
        } else {
            emptyList()
        },
        overrideable = ir.isOverride,
    )

    override fun toCodeGen(ir: IRTree.Type): CodeGenTree.Type = when (ir) {
        is IRTree.Enum -> toCodeGen(ir)
        is IRTree.NormalClass -> toCodeGen(ir)
        IRTree.Type.Builtin.BOOLEAN -> Builtin.BOOLEAN
        IRTree.Type.Builtin.DOUBLE -> Builtin.DOUBLE
        IRTree.Type.Builtin.INT -> Builtin.INT
        IRTree.Type.Builtin.LONG -> Builtin.LONG
        IRTree.Type.Builtin.STRING -> Builtin.STRING
        IRTree.Type.DateType.DATE -> DateType.DATE
        IRTree.Type.DateType.INSTANT -> DateType.INSTANT
        is IRTree.Type.LIST -> LIST(toCodeGen(ir.list))
        is IRTree.Type.MAP -> MAP(toCodeGen(ir.key), toCodeGen(ir.value))
        IRTree.Type.Builtin.UNIT -> Builtin.UNIT
        IRTree.Type.Builtin.FILE -> Builtin.FILE
        IRTree.Type.Builtin.BINARY -> Builtin.BYTEARRAY
        IRTree.Type.Builtin.BYTESTRING -> Builtin.BYTESTRING
        IRTree.Type.Builtin.FLOAT -> Builtin.FLOAT
        IRTree.Type.Builtin.UUID -> Builtin.UUID
        IRTree.Type.Builtin.DURATION -> Builtin.DURATION
    }

    override fun toCodeGen(ir: IRTree.Class): CodeGenTree.Class = when (ir) {
        is IRTree.Enum -> toCodeGen(ir)
        is IRTree.NormalClass -> toCodeGen(ir)
    }

    override fun toCodeGen(ir: IRTree.Enum) = CodeGenTree.Enum(
        packageName = ir.packageName + if (ir.packageNameSuffix != "") ".${ir.packageNameSuffix}" else "",
        names = listOf(ir.name),
        values = ir.values.map {
            CodeGenTree.Enum.Value(
                name = it.value,
                documentation = it.documentation,
                annotations = if (it.serialName != null) {
                    listOf(serialName(it.serialName!!))
                } else {
                    emptyList()
                },
            )
        },
        documentation = ir.documentation,
        annotations = listOf(SERIALIZABLE),
    )

    override fun toCodeGen(ir: IRTree.NormalClass): CodeGenTree.NormalClass = CodeGenTree.NormalClass(
        packageName = ir.packageName + if (ir.packageNameSuffix != "") ".${ir.packageNameSuffix}" else "",
        names = listOf(ir.name),
        members = ir.members.map {
            toCodeGen(it.value, name = it.key)
        },
        functions = emptyList(),
        documentation = ir.documentation,
        isFault = ir.isFault,
        annotations = buildList {
            add(SERIALIZABLE)
            val irSerialName = ir.serialName
            if (irSerialName != null) {
                add(serialName(irSerialName))
            }
        },
        types = emptyList(),
        superClassName = null,
        superInterfaces = listOfNotNull(ir.allOf?.let { toCodeGen(it) }),
    )

    override fun toCodeGen(ir: IRTree.Operation): CodeGenTree.Operation {
        return CodeGenTree.Operation(
            packageName = ir.packageName,
            name = ir.name,
            documentation = ir.documentation,
            location = ir.location,
            address = ir.address,
            input = ir.input?.let { toCodeGen(it) },
            output = ir.output?.let { toCodeGen(it) },
            fault = ir.fault?.let { toCodeGen(it) },
            method = when (ir.method) {
                IRTree.Operation.HttpMethod.Head -> CodeGenTree.Operation.HttpMethod.Head
                IRTree.Operation.HttpMethod.Get -> CodeGenTree.Operation.HttpMethod.Get
                IRTree.Operation.HttpMethod.Post -> CodeGenTree.Operation.HttpMethod.Post
                IRTree.Operation.HttpMethod.Put -> CodeGenTree.Operation.HttpMethod.Put
                IRTree.Operation.HttpMethod.Patch -> CodeGenTree.Operation.HttpMethod.Patch
                IRTree.Operation.HttpMethod.Delete -> CodeGenTree.Operation.HttpMethod.Delete
            },
            parameters = ir.parameters.map {
                it.toCodeGen(defaultNull = false)
            },
            queryParameters = ir.queryParameters.map { it.toCodeGen(defaultNull = true) },
            path = ir.path,
            inputContentType = ir.inputContentType,
            outputContentType = ir.outputContentType,
            inputWrapper = null,
            inputWrapperType = null,
            outputWrapperType = null,
            outputMember = null,
            nullableOutput = ir.nullableOutput,
            faultWrapper = null,
            success = ir.success,
            headers = ir.headers.map { it.toCodeGen(defaultNull = true) },
        )
    }

    private fun IRTree.Operation.Parameter.toCodeGen(defaultNull: Boolean): CodeGenTree.Operation.Parameter =
        CodeGenTree.Operation.Parameter(
            name = name,
            nullable = nullable,
            type = toCodeGen(type),
            documentation = documentation,
            serialName = serialName,
            defaultValue = defaultValue?.toCodeGen() ?: if (nullable && defaultNull) NullLiteral else null,
        )

    private fun IRTree.Literal.toCodeGen(): CodeGenTree.Expression = when (this) {
        is IRTree.Literal.BOOLEAN -> BooleanLiteral(value)
        is IRTree.Literal.INT -> IntLiteral(value)
        is IRTree.Literal.LONG -> LongLiteral(value)
        is IRTree.Literal.STRING -> StringLiteral(value)
        is IRTree.Literal.UUID -> UuidLiteral(value)
        is IRTree.Literal.DATE -> DateLiteral(value)
        is IRTree.Literal.DOUBLE -> DoubleLiteral(value)
        is IRTree.Literal.DURATION -> DurationLiteral(value)
        is IRTree.Literal.FLOAT -> FloatLiteral(value)
        is IRTree.Literal.INSTANT -> InstantLiteral(value)
    }

    override fun toCodeGen(ir: IRTree.ClassName): CodeGenTree.ClassName = CodeGenTree.ClassName(
        packageName = ir.packageName,
        names = ir.name.split("."),
        runtimeTypes = listOf(),
    )

    override fun toCodeGen(ir: IRTree.Auth): CodeGenTree.Auth = when (ir) {
        is IRTree.Auth.OAuth2 -> CodeGenTree.Auth.OAuth2(
            operation = toCodeGen(ir.operation),
            flow = when (ir.flow) {
                IRTree.Auth.OAuth2.Flow.Application -> CodeGenTree.Auth.OAuth2.Flow.Application
            },
            grantType = when (ir.grantType) {
                IRTree.Auth.OAuth2.GrantType.ClientCredentials -> CodeGenTree.Auth.OAuth2.GrantType.ClientCredentials
            },
        )

        is IRTree.Auth.Http -> CodeGenTree.Auth.Http(
            schema = when (ir.schema) {
                IRTree.Auth.Http.Schema.Basic -> CodeGenTree.Auth.Http.Schema.Basic
                IRTree.Auth.Http.Schema.Bearer -> CodeGenTree.Auth.Http.Schema.Bearer
            },
            name = ir.name,
            packageName = ir.packageName,
            documentation = ir.documentation,
        )
    }
}

val SERIALIZABLE = CodeGenTree.Annotation("kotlinx.serialization", listOf("Serializable"), emptyMap())
fun serialName(value: String) = CodeGenTree.Annotation(
    "kotlinx.serialization",
    listOf("SerialName"),
    mapOf("value" to StringLiteral(value)),
)
