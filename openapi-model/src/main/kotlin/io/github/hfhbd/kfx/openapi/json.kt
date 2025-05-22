package io.github.hfhbd.kfx.openapi

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

val json = Json {
    ignoreUnknownKeys = true
    useAlternativeNames = false
    serializersModule = SerializersModule {
        polymorphicDefaultDeserializer(OpenApi.Components.Schema::class) {
            OpenApi.Components.Schema.OBJECT.CustomSerializer
        }
    }
}
