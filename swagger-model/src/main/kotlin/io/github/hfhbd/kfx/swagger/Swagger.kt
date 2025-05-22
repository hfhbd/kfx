package io.github.hfhbd.kfx.swagger

import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.*

@Serializable
data class Swagger(
    @SerialName("swagger")
    val version: String,
    val info: Info,
    val basePath: String? = null,
    val tags: List<Tag> = emptyList(),
    val schemes: List<Schema> = emptyList(),
    val securityDefinitions: Map<String, SecurityDefinition>? = null,
    val security: List<Map<String, List<String>>> = emptyList(),
    val paths: Map<String, Paths>,
    val definitions: Map<String, Definition>,
    val parameters: Map<String, Parameter> = emptyMap(),
) {
    @Serializable
    public data class Info(
        val title: String,
        val description: String? = null,
        val version: String,
    )

    @Serializable
    data class Tag(val name: String)

    @Serializable
    enum class Schema {
        @SerialName("http")
        Http,

        @SerialName("https")
        Https,
    }

    @Serializable
    sealed interface SecurityDefinition {
        @SerialName("oauth2")
        @Serializable
        data class OAuth2(
            val description: String? = null,
            val flow: Flow,
            val tokenUrl: String,
            val authorizationUrl: String? = null,
            val scopes: Map<String, String> = emptyMap(),
        ) : SecurityDefinition {
            @Serializable
            enum class Flow {
                @SerialName("implicit")
                Implicit,

                @SerialName("password")
                Password,

                @SerialName("application")
                Application,

                @SerialName("accessCode")
                AccessCode,
            }
        }

        @SerialName("basic")
        @Serializable
        data class BasicAuthentication(
            val description: String? = null,
        ) : SecurityDefinition

        @SerialName("apiKey")
        @Serializable
        data class ApiKey(
            val description: String? = null,
            val name: String,
            @SerialName("in")
            val position: Position,
        ) : SecurityDefinition {
            @Serializable
            enum class Position {
                @SerialName("query")
                Query,

                @SerialName("header")
                Header,
            }
        }
    }

    @Serializable
    data class Paths(
        val head: Path? = null,
        val get: Path? = null,
        val post: Path? = null,
        val put: Path? = null,
        val patch: Path? = null,
        val delete: Path? = null,
    )

    @Serializable
    data class Path(
        val description: String? = null,
        val operationId: String? = null,
        val tags: List<String> = emptyList(),
        val produces: List<String> = emptyList(),
        val consumes: List<String> = emptyList(),
        val parameters: List<Parameter> = emptyList(),
        val responses: Map<String, Response>,
    ) {
        @Serializable
        data class Response(
            val description: String? = null,
            val schema: Definition? = null,
            val examples: JsonElement? = null,
        )
    }

    @Serializable
    public data class Definition(
        val required: List<String> = emptyList(),
        val properties: Map<String, Definition> = emptyMap(),
        val description: String? = null,
        val additionalProperties: Definition? = null,
        val readOnly: Boolean? = null,
        val example: JsonElement? = null,
        @Serializable(ItemsListSerializer::class)
        val items: Definition? = null,
        @SerialName("\$ref")
        val ref: String? = null,
        val allOf: List<Definition> = emptyList(),
        val default: JsonElement? = null,
        val discriminator: String? = null,
        @Serializable(TypeListSerializer::class)
        @SerialName("type")
        val types: List<Type> = listOf(Type.Object),
        val minLength: Int? = null,
        val maxLength: Int? = null,
        val format: String? = null,
        val nullable: Boolean = false,
        val enum: Set<String> = emptySet(),
        val minimum: Int? = null,
        val maximum: Int? = null,
        val minItems: Int? = null,
        val maxItems: Int? = null,
    ) {
        @Transient
        val type = types.filterNot { it == Type.Null }.single()

        object TypeListSerializer : JsonTransformingSerializer<List<Type>>(ListSerializer(Type.serializer())) {
            override fun transformDeserialize(element: JsonElement): JsonElement = if (element !is JsonArray) {
                JsonArray(listOf(element))
            } else {
                element
            }
        }

        object ItemsListSerializer : JsonTransformingSerializer<Definition>(Definition.serializer()) {
            override fun transformDeserialize(element: JsonElement) = if (element is JsonArray) {
                element.single()
            } else {
                element
            }
        }

        @Serializable
        enum class Type {
            @SerialName("object")
            Object,

            @SerialName("boolean")
            Boolean,

            @SerialName("number")
            Number,

            @SerialName("string")
            String,

            @SerialName("integer")
            Integer,

            @SerialName("array")
            Array,

            @SerialName("file")
            File,

            @SerialName("null")
            Null,
        }
    }

    @Serializable
    data class Parameter(
        val name: String? = null,
        val description: String? = null,
        @SerialName("in")
        val position: Position? = null,
        val required: Boolean? = null,
        val type: Types? = null,
        val enum: Set<String> = emptySet(),
        val default: JsonElement? = null,
        val minimum: JsonElement? = null,
        val maximum: JsonElement? = null,
        val schema: Definition? = null,
        @SerialName("\$ref")
        val ref: String? = null,
        val items: Definition? = null,
    ) {
        @Serializable
        enum class Types {
            @SerialName("string")
            String,

            @SerialName("integer")
            Int,

            @SerialName("boolean")
            Boolean,

            @SerialName("array")
            Array,
        }

        @Serializable
        enum class Position {
            @SerialName("body")
            Body,

            @SerialName("query")
            Query,

            @SerialName("path")
            Path,

            @SerialName("header")
            Header,
        }
    }
}
