package io.github.hfhbd.kfx.xsd

import kotlinx.serialization.modules.SerializersModule

interface XsdTransformerFactory {
    fun create(): XsdTransformer

    fun serializerModule(): SerializersModule
}
