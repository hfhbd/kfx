package io.github.hfhbd.kfx.wsdl.fir

import kotlinx.serialization.modules.SerializersModule

interface WsdlTransformerFactory {
    fun create(): WsdlTransformer

    fun serializerModule(): SerializersModule
}
