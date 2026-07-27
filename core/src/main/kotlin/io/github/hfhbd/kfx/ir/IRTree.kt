package io.github.hfhbd.kfx.ir

import io.github.hfhbd.kfx.ContentType
import io.github.hfhbd.kfx.StatusCode
import io.github.hfhbd.kfx.ir.IRTree.StringEnum.Value
import kotlinx.datetime.LocalDate
import kotlin.time.Duration
import kotlin.time.Instant
import kotlin.uuid.Uuid

data class IRTree(val classes: Set<Class>, val operations: Set<Operation>, val auth: Set<Auth>) {
    sealed interface Type {
        sealed interface Builtin : Type {
            data object CHAR : Builtin

            data object STRING : Builtin

            data object BINARY : Builtin

            data object BYTESTRING : Builtin

            data object BOOLEAN : Builtin

            data object BYTE : Builtin

            data object SHORT : Builtin

            data object INT : Builtin

            data object LONG : Builtin

            data object DOUBLE : Builtin

            data object FLOAT : Builtin

            data object DURATION : Builtin

            data object UUID : Builtin

            data object UNIT : Builtin

            data object FILE : Builtin
        }

        sealed interface DateType : Builtin {
            data object DATE : DateType

            data object INSTANT : DateType
        }

        data class LIST(val list: Type) : Type
        data class SET(val set: Type) : Type

        data class MAP(val key: Type, val value: Type) : Type

        /**
         * Like JsonObject when using kotlinx-serialization-json
         */
        data object Unknown : Type
    }

    sealed interface Class : Type {
        val packageName: String
        val packageNameSuffix: String
        val name: String
        val documentation: String?
        val deprecated: Boolean
    }

    data class NormalClass(
        override val packageName: String,
        override val packageNameSuffix: String,
        override val name: String,
        val serialName: String?,
        val namespace: String?,
        val members: Map<String, Member>,
        override val documentation: String?,
        val isFault: Boolean,
        val isValue: Boolean = false,
        val discriminator: String?,
        val allOf: ClassName?,
        override val deprecated: Boolean,
    ) : Class

    data class ClassName(val packageName: String, val name: String) {
        val qname = if (packageName.isEmpty()) name else "$packageName.$name"

        override fun equals(other: Any?): Boolean = qname == (other as? ClassName)?.qname

        override fun hashCode(): Int = qname.hashCode()

        override fun toString(): String = qname
    }

    data class Member(
        val type: Type,
        val nullable: Boolean,
        val serialName: String?,
        val namespace: String?,
        val documentation: String?,
        val xmlType: XmlType?,
        val requirements: List<Requirement>,
        val isOverride: Boolean,
        val deprecated: Boolean,
    ) {
        sealed interface Requirement {
            data class MinLength(val inclusive: Int) : Requirement

            data class MaxLength(val inclusive: Int) : Requirement
        }
    }

    enum class XmlType {
        Element,
        Value,
        Attribute,
        CData,
    }

    sealed interface Enum : Class

    data class StringEnum(
        override val packageName: String,
        override val packageNameSuffix: String,
        override val name: String,
        val values: List<Value>,
        override val documentation: String?,
        override val deprecated: Boolean,
    ) : Enum {
        data class Value(val value: String, val documentation: String?, val serialName: String?)
    }

    data class LongEnum(
        override val packageName: String,
        override val packageNameSuffix: String,
        override val name: String,
        val values: List<Value>,
        override val documentation: String?,
        override val deprecated: Boolean,
    ) : Enum {
        data class Value(val value: Long, val documentation: String?)
    }

    data class Operation(
        val packageName: String,
        val name: String,
        val documentation: String? = null,
        val method: HttpMethod,
        val path: String?,
        val parameters: List<Parameter> = emptyList(),
        val headers: List<Parameter> = emptyList(),
        val queryParameters: List<Parameter> = emptyList(),
        val location: String? = null,
        val soapAction: String? = null,
        val success: StatusCode? = null,
        val input: Type?,
        val inputContentType: ContentType?,
        val output: Type?,
        val outputContentType: ContentType?,
        val outputHeaders: List<Parameter> = emptyList(),
        val notFound: Boolean,
        val fault: NormalClass? = null,
        val faultHeaders: List<Parameter> = emptyList(),
        val deprecated: Boolean = false,
    ) {
        enum class HttpMethod {
            Head,
            Get,
            Post,
            Put,
            Patch,
            Delete,
            Options,
            Trace,
            Query,
        }

        data class Parameter(
            val name: String,
            val serialName: String? = null,
            val type: Type,
            val nullable: Boolean,
            val documentation: String? = null,
            val defaultValue: Literal? = null,
            val deprecated: Boolean = false,
        )
    }

    sealed interface Literal {
        data class CHAR(val value: Char) : Literal

        data class STRING(val value: String) : Literal

        data class BYTE(val value: Byte) : Literal

        data class SHORT(val value: Short) : Literal

        data class INT(val value: Int) : Literal

        data class LONG(val value: Long) : Literal

        data class FLOAT(val value: Float) : Literal

        data class DOUBLE(val value: Double) : Literal

        data class DURATION(val value: Duration) : Literal

        data class DATE(val value: LocalDate) : Literal

        data class INSTANT(val value: Instant) : Literal

        data class UUID(val value: Uuid) : Literal

        data class BOOLEAN(val value: Boolean) : Literal
    }

    sealed interface Auth {
        data class OAuth2(val flow: Flow, val operation: Operation, val grantType: GrantType) : Auth {
            enum class Flow {
                Application,
            }

            enum class GrantType {
                ClientCredentials,
            }
        }

        data class Http(val schema: Schema, val name: String, val packageName: String, val documentation: String?) :
            Auth {
            sealed interface Schema {
                data object Basic : Schema

                data object Bearer : Schema

                data class Header(val headerName: String) : Schema
            }
        }
    }
}
