package io.github.hfhbd.kfx.wsdl

import kotlinx.serialization.modules.SerializersModule

interface WsdlTransformerFactory {
    fun serializerModule(): SerializersModule
    fun create(): WsdlTransformer
}
