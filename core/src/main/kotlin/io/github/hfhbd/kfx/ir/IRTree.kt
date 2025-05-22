package io.github.hfhbd.kfx.ir

import io.github.hfhbd.kfx.ContentType
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.*
import kotlin.time.Duration
import kotlin.uuid.Uuid

@Serializable
data class IRTree(
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
            data object BINARY : Builtin

            @Serializable
            data object BYTESTRING : Builtin

            @Serializable
            data object BOOLEAN : Builtin

            @Serializable
            data object INT : Builtin

            @Serializable
            data object LONG : Builtin

            @Serializable
            data object DOUBLE : Builtin

            @Serializable
            data object FLOAT : Builtin

            @Serializable
            data object DURATION : Builtin

            @Serializable
            data object UUID : Builtin

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
        data class LIST(val list: Type) : Type
    }

    @Serializable
    sealed interface Class : Type {
        val packageName: String
        val packageNameSuffix: String
        val name: String
        val documentation: String?
    }

    @Serializable
    data class NormalClass(
        override val packageName: String,
        override val packageNameSuffix: String,
        override val name: String,
        val serialName: String?,
        val namespace: String?,
        val members: Map<String, Member>,
        override val documentation: String?,
        val isFault: Boolean,
        val discriminator: String?,
        val allOf: ClassName?,
    ) : Class

    @Serializable
    data class ClassName(val packageName: String, val name: String) {
        val qname = if (packageName.isEmpty()) name else "$packageName.$name"
        override fun equals(other: Any?): Boolean = qname == (other as? ClassName)?.qname
        override fun hashCode(): Int = qname.hashCode()

        override fun toString(): String = qname
    }

    @Serializable
    data class Member(
        val type: Type,
        val nullable: Boolean,
        val serialName: String?,
        val namespace: String?,
        val documentation: String?,
        val xmlType: XmlType?,
        val requirements: List<Requirement>,
        val isOverride: Boolean,
    ) {
        @Serializable
        sealed interface Requirement {
            @Serializable
            data class MinLength(val inclusive: Int) : Requirement

            @Serializable
            data class MaxLength(val inclusive: Int) : Requirement
        }
    }

    @Serializable
    enum class XmlType {
        Element, Value, Attribute, CData
    }

    @Serializable
    data class Enum(
        override val packageName: String,
        override val packageNameSuffix: String,
        override val name: String,
        val values: List<Value>,
        override val documentation: String?,
    ) : Class {
        @Serializable
        data class Value(
            val value: String,
            val documentation: String?,
            val serialName: String?,
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
        val headers: List<Parameter>,
        val queryParameters: List<Parameter>,

        val location: String?,
        val address: String?,

        val success: Int?,

        val input: Type?,
        val inputContentType: ContentType?,
        val output: Type?,
        val outputContentType: ContentType?,
        val nullableOutput: Int?,
        val fault: NormalClass?,
    ) {
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
            val defaultValue: Literal?,
        )
    }

    @Serializable
    sealed interface Literal {
        @Serializable
        data class STRING(val value: String) : Literal

        @Serializable
        data class INT(val value: Int) : Literal

        @Serializable
        data class LONG(val value: Long) : Literal

        @Serializable
        data class FLOAT(val value: Float) : Literal

        @Serializable
        data class DOUBLE(val value: Double) : Literal

        @Serializable
        data class DURATION(val value: Duration) : Literal

        @Serializable
        data class DATE(val value: LocalDate) : Literal

        @Serializable
        data class INSTANT(val value: Instant) : Literal

        @Serializable
        data class UUID(
            val value: Uuid,
        ) : Literal

        @Serializable
        data class BOOLEAN(val value: Boolean) : Literal
    }

    @Serializable
    sealed interface Auth {
        @Serializable
        data class OAuth2(
            val flow: Flow,
            val operation: Operation,
            val grantType: GrantType,
        ) : Auth {
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
