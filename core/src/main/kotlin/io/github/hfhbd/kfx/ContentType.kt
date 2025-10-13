package io.github.hfhbd.kfx

import kotlinx.serialization.Serializable

@Serializable
sealed interface ContentType {
    @Serializable
    data object ApplicationSoapXml : ContentType {
        override fun toString(): String = "application/soap+xml"
    }

    @Serializable
    data object ApplicationXml : ContentType {
        override fun toString(): String = "application/xml"
    }

    @Serializable
    data object ApplicationJson : ContentType {
        override fun toString(): String = "application/json"
    }

    @Serializable
    data object ApplicationProblemJson : ContentType {
        override fun toString(): String = "application/problem+json"
    }

    @Serializable
    data object ApplicationProblemXml : ContentType {
        override fun toString(): String = "application/problem+xml"
    }

    @Serializable
    data object ApplicationZip : ContentType {
        override fun toString(): String = "application/zip"
    }

    @Serializable
    data object FormUrlEncoded : ContentType {
        override fun toString(): String = "application/x-www-form-urlencoded"
    }

    @Serializable
    data object MultipartFormData : ContentType {
        override fun toString(): String = "multipart/form-data"
    }

    @Serializable
    data object OctetStream : ContentType {
        override fun toString(): String = "application/octet-stream"
    }

    @Serializable
    data object TextPlain : ContentType {
        override fun toString(): String = "text/plain"
    }

    @Serializable
    data object TextCsv : ContentType {
        override fun toString(): String = "text/csv"
    }

    @Serializable
    data object TextXml : ContentType {
        override fun toString(): String = "text/xml"
    }

    @Serializable
    data class Custom(val contentType: String) : ContentType {
        init {
            require(contentType.isNotBlank())
        }

        override fun toString() = contentType
    }

    companion object {
        fun fromString(contentType: String): ContentType? {
            if (contentType.isBlank()) return null
            return when (contentType.split(";").first()) {
                "application/json" -> ApplicationJson
                "application/problem+json" -> ApplicationProblemJson
                "application/problem+xml" -> ApplicationProblemXml
                "application/xml" -> ApplicationXml
                "application/zip" -> ApplicationZip
                "application/soap+xml" -> ApplicationSoapXml
                "application/x-www-form-urlencoded" -> FormUrlEncoded
                "multipart/form-data" -> MultipartFormData
                "application/octet-stream" -> OctetStream
                "text/plain" -> TextPlain
                "text/csv" -> TextCsv
                "text/xml" -> TextXml
                else -> Custom(contentType)
            }
        }
    }
}
