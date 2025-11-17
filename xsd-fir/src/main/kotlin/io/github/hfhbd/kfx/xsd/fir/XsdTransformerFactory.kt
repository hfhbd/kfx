package io.github.hfhbd.kfx.xsd.fir

import kotlinx.serialization.modules.SerializersModule

interface XsdTransformerFactory {
    fun create(): XsdTransformer

    fun serializerModule(): SerializersModule
}
