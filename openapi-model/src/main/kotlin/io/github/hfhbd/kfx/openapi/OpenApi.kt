@file:OptIn(InternalSerializationApi::class)

package io.github.hfhbd.kfx.openapi

import io.github.hfhbd.kfx.openapi.OpenApi.Components.Schema.OBJECT.OneOf.Companion.emptyOneOf
import io.github.hfhbd.kfx.openapi.OpenApi.Components.Schema.OBJECT.Properties.Companion.emptyProperties
import io.github.hfhbd.kfx.openapi.OpenApi.Operation.Response
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*

@KeepGeneratedSerializer
@Serializable(with = OpenApi.CustomSerializer::class)
public data class OpenApi(
    @SerialName("openapi")
    val version: String,

    val info: Info,
    val tags: List<Tag> = emptyList(),
    val servers: List<Server> = emptyList(),
    val paths: Map<String, Path>,
    val components: Components,
    val security: List<Map<String, List<String>>> = emptyList(),
    val externalDocs: ExternalDocs? = null,
    val extensions: Map<String, JsonElement> = emptyMap(),
) {
    internal object CustomSerializer : KSerializerWithExtensions<OpenApi>(
        generatedSerializer(),
        OpenApi::extensions,
        { copy(extensions = it) },
    )

    init {
        checkUniqueId()
    }

    private fun checkUniqueId() {
        val operationIds = mutableSetOf<String>()
        paths.values.forEach {
            if (it.head != null) {
                operationIds.checkId(it.head.id)
            }
            if (it.get != null) {
                operationIds.checkId(it.get.id)
            }
            if (it.post != null) {
                operationIds.checkId(it.post.id)
            }
            if (it.put != null) {
                operationIds.checkId(it.put.id)
            }
            if (it.patch != null) {
                operationIds.checkId(it.patch.id)
            }
            if (it.delete != null) {
                operationIds.checkId(it.delete.id)
            }
        }
    }

    private fun MutableSet<String>.checkId(id: String) {
        require(add(id)) {
            "Duplicate unique operationID found: $id"
        }
    }

    @Serializable(with = Info.Companion.Serializer::class)
    @KeepGeneratedSerializer
    public data class Info(
        val title: String,
        val summary: String? = null,
        val description: String? = null,
        val termsOfService: String? = null,
        val contact: Contact? = null,
        val license: License? = null,
        val version: String,
        val extensions: Map<String, JsonElement> = emptyMap(),
    ) {
        companion object {
            internal object Serializer : KSerializerWithExtensions<Info>(
                generatedSerializer(),
                Info::extensions,
                { copy(extensions = it) },
            )
        }

        @Serializable
        data class License(
            val name: String,
            val url: String? = null,
            val email: String? = null,
        )

        @Serializable
        data class Contact(
            val name: String? = null,
            val identifier: String? = null,
            val url: String? = null,
        )
    }

    @Serializable
    data class Tag(
        val name: String,
        val description: String? = null,
        val externalDocs: ExternalDocs? = null,
    )

    @Serializable
    data class ExternalDocs(
        val url: String,
        val description: String? = null,
    )

    @Serializable
    public data class Server(
        val url: String,
        val description: String? = null,
        val variables: Map<String, Variable> = emptyMap(),
    ) {
        @Serializable
        data class Variable(
            val description: String? = null,
            val enum: List<String> = emptyList(),
            val default: String,
        )
    }

    @Serializable
    data class Path(
        val parameters: List<Parameter> = emptyList(),
        val head: Operation? = null,
        val get: Operation? = null,
        val post: Operation? = null,
        val put: Operation? = null,
        val patch: Operation? = null,
        val delete: Operation? = null,
    )

    @KeepGeneratedSerializer
    @Serializable(with = Operation.CustomSerializer::class)
    public data class Operation(
        @SerialName("operationId")
        val id: String,
        val summary: String? = null,
        val description: String? = null,
        val tags: List<String> = emptyList(),
        val requestBody: RequestBody? = null,
        val parameters: List<Parameter> = emptyList(),
        val responses: Map<String, Response>,
        val security: List<Map<String, List<String>>> = emptyList(),
        val externalDocs: ExternalDocs? = null,
        val extensions: Map<String, JsonElement> = emptyMap(),
        val deprecated: Boolean = false,
        val servers: List<Server> = emptyList(),
    ) {
        internal object CustomSerializer : KSerializerWithExtensions<Operation>(
            Operation.generatedSerializer(),
            Operation::extensions,
            { copy(extensions = it) },
        )

        @Serializable
        public data class RequestBody(
            val description: String? = null,
            val required: Boolean? = false,
            val content: Map<String, MediaType>,
        )

        @Serializable
        public data class Response(
            val description: String? = null,
            val headers: Map<String, Header> = emptyMap(),
            val content: Map<String, MediaType> = emptyMap(),
            @SerialName("\$ref")
            val ref: String? = null,
        ) {
            public companion object {
                public const val default: String = "default"
            }
        }

        @Serializable
        data class Header(
            @SerialName("\$ref")
            val ref: String? = null,
            val description: String? = null,
            val required: Boolean = false,
            val deprecated: Boolean = false,
            val allowEmptyValue: Boolean = false,
            val schema: Components.Schema? = null,
            val example: JsonElement? = null,
        )

        @Serializable
        public data class MediaType(
            val schema: Components.Schema? = null,
            val examples: JsonElement? = null,
        )
    }

    @Serializable
    public data class Components(
        val schemas: Map<String, Schema>,
        val securitySchemes: Map<String, SecurityScheme> = emptyMap(),
        val parameters: Map<String, Parameter> = emptyMap(),
        val examples: JsonElement? = null,
        val responses: Map<String, Response> = emptyMap(),
        val headers: Map<String, Operation.Header> = emptyMap(),
    ) {
        @Serializable
        public sealed interface Schema {
            public val title: String?
            public val description: String?
            public val extensions: Map<String, JsonElement>
            public val nullable: Boolean?
            public val deprecated: Boolean
            public val readOnly: Boolean
            public val additionalProperties: JsonElement?

            @KeepGeneratedSerializer
            @Serializable(with = OBJECT.CustomSerializer::class)
            @SerialName("object")
            public data class OBJECT(
                @SerialName("\$ref")
                val ref: String? = null,
                val required: List<String>? = null,
                val properties: Properties = emptyProperties(),
                override val additionalProperties: JsonElement? = null,
                override val description: String? = null,
                override val title: String? = null,
                override val extensions: Map<String, JsonElement> = emptyMap(),
                val discriminator: Discriminator? = null,
                val oneOf: OneOf = emptyOneOf(),
                val allOf: OneOf = emptyOneOf(),
                val anyOf: OneOf = emptyOneOf(),
                val example: JsonElement? = null,
                override val nullable: Boolean? = null,
                val maxProperties: Int? = null,
                val enum: List<String> = emptyList(),
                override val deprecated: Boolean = false,
                override val readOnly: Boolean = false,
            ) : Schema {
                val additionalPropertiesSchema
                    get() = if (additionalProperties == null) {
                        null
                    } else {
                        json.decodeFromJsonElement(
                            Schema.serializer(),
                            additionalProperties,
                        )
                    }

                internal object CustomSerializer : KSerializerWithExtensions<OBJECT>(
                    OBJECT.generatedSerializer(),
                    OBJECT::extensions,
                    { copy(extensions = it) },
                )

                @Serializable
                @JvmInline
                value class Properties(private val properties: Map<String, Schema>) :
                    Map<String, Schema> by properties {
                    companion object {
                        fun emptyProperties(): Properties = Properties(emptyMap())
                    }
                }

                @Serializable
                @JvmInline
                value class OneOf(private val oneOf: List<Schema>) : List<Schema> by oneOf {
                    companion object {
                        fun emptyOneOf(): OneOf = OneOf(emptyList())
                    }
                }

                @Serializable
                data class Discriminator(
                    val propertyName: String,
                    val mapping: Map<String, String> = emptyMap(),
                )
            }

            @KeepGeneratedSerializer
            @Serializable(with = STRING.CustomSerializer::class)
            @SerialName("string")
            public data class STRING(
                override val title: String? = null,
                override val description: String? = null,
                val minLength: Int? = null,
                val maxLength: Int? = null,
                override val nullable: Boolean = false,
                val default: String? = null,
                val enum: List<String?> = emptyList(),
                override val extensions: Map<String, JsonElement> = emptyMap(),
                val example: String? = null,
                val pattern: String? = null,
                override val deprecated: Boolean = false,
                override val readOnly: Boolean = false,
                override val additionalProperties: JsonElement? = null,
                val format: Format? = null,
            ) : Schema {
                internal object CustomSerializer : KSerializerWithExtensions<STRING>(
                    STRING.generatedSerializer(),
                    STRING::extensions,
                    { copy(extensions = it) },
                )

                // https://datatracker.ietf.org/doc/html/draft-bhutton-json-schema-validation-00#section-7.3
                @Serializable
                @JvmInline
                value class Format(public val value: String) {
                    companion object {
                        val Byte = Format("byte")
                        val Binary = Format("binary")
                        val Date = Format("date")
                        val DateTime = Format("date-time")
                        val Password = Format("password")
                        val Duration = Format("duration")
                        val Uuid = Format("uuid")
                    }
                }
            }

            @KeepGeneratedSerializer
            @Serializable(with = INT.CustomSerializer::class)
            @SerialName("integer")
            public data class INT(
                val format: Format = Format.Int64,
                override val description: String? = null,
                override val nullable: Boolean = false,
                val default: Long? = null,
                val minimum: Long? = null,
                val maximum: Long? = null,
                override val extensions: Map<String, JsonElement> = emptyMap(),
                val example: Long? = null,
                override val deprecated: Boolean = false,
                override val title: String? = null,
                override val readOnly: Boolean = false,
                override val additionalProperties: JsonElement? = null,
            ) : Schema {
                internal object CustomSerializer : KSerializerWithExtensions<INT>(
                    INT.generatedSerializer(),
                    INT::extensions,
                    { copy(extensions = it) },
                )

                @Serializable
                enum class Format {
                    @SerialName("int32")
                    Int32,

                    @SerialName("int64")
                    Int64,
                }
            }

            @KeepGeneratedSerializer
            @Serializable(with = NUMBER.CustomSerializer::class)
            @SerialName("number")
            public data class NUMBER(
                val format: Format = Format.Double,
                override val description: String? = null,
                override val nullable: Boolean = false,
                override val extensions: Map<String, JsonElement> = emptyMap(),
                val minimum: Double? = null,
                val maximum: Double? = null,
                val default: Double? = null,
                override val deprecated: Boolean = false,
                override val readOnly: Boolean = false,
                val example: Double? = null,
                override val title: String? = null,
                override val additionalProperties: JsonElement? = null,
            ) : Schema {
                internal object CustomSerializer : KSerializerWithExtensions<NUMBER>(
                    NUMBER.generatedSerializer(),
                    NUMBER::extensions,
                    { copy(extensions = it) },
                )

                @Serializable
                enum class Format {
                    @SerialName("float")
                    Float,

                    @SerialName("double")
                    Double,
                }
            }

            @KeepGeneratedSerializer
            @Serializable(with = BOOLEAN.CustomSerializer::class)
            @SerialName("boolean")
            public data class BOOLEAN(
                override val description: String? = null,
                override val nullable: Boolean = false,
                override val readOnly: Boolean = false,
                val default: Boolean? = null,
                override val extensions: Map<String, JsonElement> = emptyMap(),
                val example: Boolean? = null,
                override val deprecated: Boolean = false,
                override val title: String? = null,
                override val additionalProperties: JsonElement? = null,
            ) : Schema {
                internal object CustomSerializer : KSerializerWithExtensions<BOOLEAN>(
                    BOOLEAN.generatedSerializer(),
                    BOOLEAN::extensions,
                    { copy(extensions = it) },
                )
            }

            @KeepGeneratedSerializer
            @Serializable(with = ARRAY.CustomSerializer::class)
            @SerialName("array")
            public data class ARRAY(
                val items: Schema? = null,
                val maxItems: Int? = null,
                override val description: String? = null,
                @SerialName("\$ref")
                val ref: String? = null,
                override val extensions: Map<String, JsonElement> = emptyMap(),
                val example: JsonElement? = null,
                val minItems: Int? = null,
                val default: List<JsonElement> = emptyList(),
                override val nullable: Boolean = false,
                override val deprecated: Boolean = false,
                override val readOnly: Boolean = false,
                override val title: String? = null,
                override val additionalProperties: JsonElement? = null,
                val required: List<String>? = null,
            ) : Schema {
                internal object CustomSerializer : KSerializerWithExtensions<ARRAY>(
                    ARRAY.generatedSerializer(),
                    ARRAY::extensions,
                    { copy(extensions = it) },
                )
            }
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    @KeepGeneratedSerializer
    @Serializable(with = Parameter.CustomSerializer::class)
    data class Parameter(
        @SerialName("\$ref")
        val ref: String? = null,
        val name: String? = null,
        @SerialName("in")
        val position: Position? = null,
        val description: String? = null,
        val required: Boolean = false,
        val deprecated: Boolean = false,
        val allowEmptyValue: Boolean = false,
        val schema: Components.Schema? = null,
        val example: JsonElement? = null,
        val examples: JsonElement? = null,
        val extensions: Map<String, JsonElement> = emptyMap(),
    ) {
        @Serializable
        enum class Position {
            @SerialName("header")
            Header,

            @SerialName("query")
            Query,

            @SerialName("path")
            Path,

            @SerialName("cookie")
            Cookie,
        }

        internal object CustomSerializer : KSerializerWithExtensions<Parameter>(
            Parameter.generatedSerializer(),
            Parameter::extensions,
            { copy(extensions = it) },
        )
    }

    @Serializable
    sealed interface SecurityScheme {
        val description: String?

        @Serializable
        @SerialName("apiKey")
        data class ApiKey(
            override val description: String? = null,
            val name: String,
            @SerialName("in")
            val position: Position,
        ) : SecurityScheme {
            @Serializable
            enum class Position {
                @SerialName("query")
                Query,

                @SerialName("header")
                Header,

                @SerialName("cookie")
                Cookie,
            }
        }

        @Serializable
        @SerialName("http")
        data class Http(
            override val description: String? = null,
            val scheme: Scheme,
            val bearerFormat: String? = null,
        ) : SecurityScheme {
            @Serializable
            enum class Scheme {
                @SerialName("basic")
                Basic,

                @SerialName("bearer")
                Bearer,
            }
        }

        @Serializable
        @SerialName("mutualTLS")
        data class MutualTLS(
            override val description: String? = null,
        ) : SecurityScheme

        @Serializable
        @SerialName("oauth2")
        data class OAuth2(
            override val description: String? = null,
            val flows: Flows,
        ) : SecurityScheme {
            @Serializable
            data class Flows(
                val implicit: Implicit? = null,
                val password: Password? = null,
                val clientCredentials: ClientCredentials? = null,
                val authorizationCode: AuthorizationCode? = null,
            ) {
                @Serializable
                @SerialName("implicit")
                data class Implicit(
                    val authorizationUrl: String,
                    val refreshUrl: String? = null,
                    val scopes: Map<String, String>,
                )

                @Serializable
                @SerialName("password")
                data class Password(
                    val tokenUrl: String,
                    val refreshUrl: String? = null,
                    val scopes: Map<String, String>,
                )

                @Serializable
                @SerialName("clientCredentials")
                data class ClientCredentials(
                    val tokenUrl: String,
                    val refreshUrl: String? = null,
                    val scopes: Map<String, String>,
                )

                @Serializable
                @SerialName("authorizationCode")
                data class AuthorizationCode(
                    val authorizationUrl: String,
                    val tokenUrl: String,
                    val refreshUrl: String? = null,
                    val scopes: Map<String, String>,
                )
            }
        }

        @Serializable
        @SerialName("openIdConnect")
        data class OpenIdConnect(
            override val description: String? = null,
            val openIdConnectUrl: String,
        ) : SecurityScheme
    }
}

internal abstract class KSerializerWithExtensions<T>(
    private val serializer: KSerializer<T>,
    private val extensions: (T) -> Map<String, JsonElement>,
    private val withExtensions: T.(Map<String, JsonElement>) -> T,
) : KSerializer<T> {
    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun deserialize(decoder: Decoder): T {
        decoder as JsonDecoder
        val jsObject = decoder.decodeJsonElement() as JsonObject
        val value = decoder.json.decodeFromJsonElement(
            serializer,
            JsonObject(
                jsObject.filterNot { (key, _) -> key.startsWith("x-") } - "type",
            ),
        )
        val extensions = jsObject.jsonObject.filter { (key, _) -> key.startsWith("x-") }
        return value.withExtensions(extensions)
    }

    override fun serialize(encoder: Encoder, value: T) {
        encoder as JsonEncoder
        val jsObject = (encoder.json.encodeToJsonElement(serializer, value) as JsonObject) - "extensions"
        encoder.encodeJsonElement(JsonObject(jsObject + extensions(value)))
    }
}
