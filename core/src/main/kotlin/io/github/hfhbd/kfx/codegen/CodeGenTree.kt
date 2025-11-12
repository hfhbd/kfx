package io.github.hfhbd.kfx.codegen

import io.github.hfhbd.kfx.*
import kotlinx.datetime.*
import kotlinx.serialization.*
import kotlin.time.*
import kotlin.time.Instant
import kotlin.uuid.*

@Serializable
data class CodeGenTree(
    val classes: Set<Class>,
    val operations: Set<Operation>,
    val auth: Set<Auth>,
) {
    @Serializable
    sealed interface Type {
        @Serializable
        sealed interface Builtin : Type {
            @Serializable
            data object STRING : Builtin

            @Serializable
            data object CHAR : Builtin

            @Serializable
            data object CHARARRAY : Builtin

            @Serializable
            data object BYTESTRING : Builtin

            @Serializable
            data object BYTEARRAY : Builtin

            @Serializable
            data object BOOLEAN : Builtin

            @Serializable
            data object BYTE : Builtin

            @Serializable
            data object SHORT : Builtin

            @Serializable
            data object INT : Builtin

            @Serializable
            data object LONG : Builtin

            @Serializable
            data object DOUBLE : Builtin

            @Serializable
            data object FLOAT : Builtin

            @Serializable
            data object UUID : Builtin

            @Serializable
            data object DURATION : Builtin

            @Serializable
            data object UNIT : Builtin

            @Serializable
            data object FILE : Builtin
        }

        @Serializable
        sealed interface DateType : Builtin {
            @Serializable
            data object DATE : DateType

            @Serializable
            data object INSTANT : DateType
        }

        @Serializable
        data class LIST(val item: Type) : Type

        @Serializable
        data class ARRAY(val item: Type) : Type

        @Serializable
        data class MAP(val key: Type, val value: Type) : Type

        @Serializable
        data class Parameter(val name: String, val upperBound: List<Type>) : Type

        @Serializable
        data object STAR : Type
    }

    @Serializable
    sealed interface Class : Type {
        val packageName: String
        val names: List<String>
        val documentation: String?
        val annotations: List<Annotation>

        val qualifiedName: String get() = if (packageName.isEmpty()) {
            names.joinToString(".")
        } else {
            "$packageName.${names.joinToString(".")}"
        }

        val innerClasses: List<Class>
    }

    @Serializable
    data class Annotation(
        val packageName: String,
        val names: List<String>,
        val values: Map<String, Expression.ConstExpression>,
    )

    @Serializable
    data class NormalClass(
        override val packageName: String,
        override val names: List<String>,
        val members: List<Member> = emptyList(),
        val functions: List<Function> = emptyList(),
        override val documentation: String? = null,
        val isFault: Boolean = false,
        val isCompanion: Boolean = false,
        val isStatic: Boolean = false,
        override val annotations: List<Annotation> = emptyList(),
        val types: List<Type> = emptyList(),
        val isSealed: Boolean = false,
        val superClassName: ClassName? = null,
        val superInterfaces: List<ClassName> = emptyList(),
        override val innerClasses: List<Class> = emptyList(),
    ) : Class

    @Serializable
    data class Member(
        val name: String,
        val type: Type,
        val nullable: Boolean = false,
        val documentation: String? = null,
        val annotations: List<Annotation> = emptyList(),
        val mutable: Boolean = false,
        val overrideable: Boolean = false,
    )

    @Serializable
    data class Function(
        val name: String,
        val parameters: List<Parameter>,
        val returnType: Type,
        val nullable: Boolean = false,
        val suspending: Boolean = false,
        val packageName: String? = null,
        val documentation: String? = null,
        val annotations: List<Annotation> = emptyList(),
        val fileName: String? = null,
    ) {
        @Serializable
        data class Parameter(
            val name: String,
            val type: Type,
            val nullable: Boolean = false,
            val documentation: String? = null,
            val defaultValue: Expression? = null,
            val annotations: List<Annotation> = emptyList(),
        )
    }

    @Serializable
    data class ClassName(
        val packageName: String,
        val names: List<String>,
        val runtimeTypes: List<Type> = emptyList(),
    ) {
        val qualifiedName: String
            get() = if (packageName.isEmpty()) {
                names.joinToString(".")
            } else {
                "$packageName.${names.joinToString(".")}"
            }
    }

    @Serializable
    data class Enum(
        override val packageName: String,
        override val names: List<String>,
        val values: List<Value>,
        override val documentation: String? = null,
        override val annotations: List<Annotation> = emptyList(),
        override val innerClasses: List<Class> = emptyList(),
    ) : Class {
        @Serializable
        data class Value(
            val name: String,
            val documentation: String? = null,
            val annotations: List<Annotation> = emptyList(),
        )
    }

    @Serializable
    data class Operation(
        val packageName: String,
        val name: String,
        val documentation: String?,

        val method: HttpMethod,
        val path: String?,
        val parameters: List<Parameter>,
        val queryParameters: List<Parameter>,
        val headers: List<Parameter>,

        val location: String?,
        val address: String?,

        val input: Type?,
        val output: Type?,
        val outputHeaders: List<Parameter>,
        val returnType: Type?,
        val success: StatusCode,
        val notFound: Boolean,
        val fault: NormalClass?,
        val faultHeaders: List<Parameter>,
        val inputContentType: ContentType?,
        val outputContentType: ContentType?,

        val inputWrapper: Expression?,
        val inputWrapperType: Type?,
        val outputWrapperType: Type?,
        val outputMember: Expression?,
        val responseBranches: ResponseBranches?,

        val faultWrapper: Type?,
        val deprecated: Boolean,
    ) {
        @Serializable
        data class ResponseBranches(
            val success: Branch?,
            val notFound: Branch?,
            val fault: Branch,
        ) {
            @Serializable
            data class Branch(
                val isCondition: NormalClass,
                val statusCode: StatusCode,
                val response: Expression?,
                val headers: Map<String, Pair<String, Boolean>>,
            )
        }

        @Serializable
        enum class HttpMethod {
            Head, Get, Post, Put, Patch, Delete
        }

        @Serializable
        data class Parameter(
            val name: String,
            val serialName: String?,
            val type: Type,
            val nullable: Boolean,
            val documentation: String?,
            val defaultValue: Expression?,
            val annotations: List<Annotation> = emptyList(),
        )
    }

    @Serializable
    sealed interface Expression {
        @Serializable
        data object Input : Expression

        @Serializable
        data object Output : Expression

        @Serializable
        data object Response : Expression

        @Serializable
        data class Chain(val lhs: Expression, val rhs: Expression) : Expression

        @Serializable
        data class Plus(val lhs: Expression, val rhs: Expression) : Expression

        @Serializable
        data class CallMember(val member: Member) : Expression

        @Serializable
        data class CallFunction(val function: Function, val parameters: List<Pair<String, Expression>>) : Invokable

        @Serializable
        sealed interface ConstExpression : Expression

        @Serializable
        sealed interface Invokable : Expression

        @Serializable
        data class CharLiteral(val value: Char) : ConstExpression

        @Serializable
        data class StringLiteral(val value: String) : ConstExpression

        @Serializable
        data class ByteLiteral(val value: Byte) : ConstExpression

        @Serializable
        data class ShortLiteral(val value: Short) : ConstExpression

        @Serializable
        data class IntLiteral(val value: Int) : ConstExpression

        @Serializable
        data class LongLiteral(val value: Long) : ConstExpression

        @Serializable
        data class FloatLiteral(val value: Float) : ConstExpression

        @Serializable
        data class DoubleLiteral(val value: Double) : ConstExpression

        @Serializable
        data class ClassLiteral(val value: ClassName) : ConstExpression {
            init {
                require(value.runtimeTypes.isEmpty()) {
                    "ClassLiteral does not support generics. Was $value"
                }
            }
        }

        @Serializable
        data class EnumLiteral(val qualifiedName: ClassName, val member: String) : ConstExpression

        @Serializable
        data class DurationLiteral(val value: Duration) : Expression

        @Serializable
        data class UuidLiteral(val value: Uuid) : Expression

        @Serializable
        data class DateLiteral(val value: LocalDate) : Expression

        @Serializable
        data class InstantLiteral(val value: Instant) : Expression

        @Serializable
        data class BooleanLiteral(val value: Boolean) : ConstExpression, BooleanExpression

        @Serializable
        data object NullLiteral : Expression

        @Serializable
        data class Create(val normalClass: NormalClass, val parameters: List<Pair<String, Expression>>) : Invokable

        @Serializable
        data class Parameter(val parameter: Operation.Parameter) : Expression

        @Serializable
        data class ListOf(val expressions: List<Expression>) : Expression

        @Serializable
        data class ArrayOf(val expressions: List<Expression>) : Expression

        @Serializable
        data class ConstArrayOf(val expressions: List<ConstExpression>) : Expression

        @Serializable
        data class CallStatic(val type: Type) : Invokable

        sealed interface BooleanExpression : Expression

        @Serializable
        data class Is(val type: Type) : BooleanExpression
    }

    @Serializable
    sealed interface Auth {
        @Serializable
        data class OAuth2(val flow: Flow, val operation: Operation, val grantType: GrantType) : Auth {
            @Serializable
            enum class Flow {
                Application,
            }

            @Serializable
            enum class GrantType {
                ClientCredentials,
            }
        }

        @Serializable
        data class Http(
            val schema: Schema,
            val name: String,
            val packageName: String,
            val documentation: String?,
        ) : Auth {
            @Serializable
            enum class Schema {
                Basic,
                Bearer,
            }
        }
    }
}
