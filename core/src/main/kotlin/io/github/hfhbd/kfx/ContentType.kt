package io.github.hfhbd.kfx

import kotlinx.serialization.Serializable

@Serializable
sealed interface ContentType {
    @Serializable
    data object ApplicationSoapXml : ContentType

    @Serializable
    data object ApplicationJson : ContentType

    @Serializable
    data object ApplicationZip : ContentType

    @Serializable
    data object FormUrlEncoded : ContentType

    @Serializable
    data object MultipartFormData : ContentType

    @Serializable
    data object OctetStream : ContentType

    @Serializable
    data object TextPlain : ContentType

    @Serializable
    data object TextCsv : ContentType

    @Serializable
    data class Custom(val contentType: String) : ContentType {
        init {
            require(contentType.isNotBlank())
        }
    }

    companion object {
        fun fromString(contentType: String): ContentType? {
            if (contentType.isBlank()) return null
            return when (contentType.split(";").first()) {
                "application/json" -> ApplicationJson
                "application/zip" -> ApplicationZip
                "application/soap+xml" -> ApplicationSoapXml
                "application/x-www-form-urlencoded" -> FormUrlEncoded
                "multipart/form-data" -> MultipartFormData
                "application/octet-stream" -> OctetStream
                "text/plain" -> TextPlain
                "text/csv" -> TextCsv
                else -> Custom(contentType)
            }
        }
    }
}
