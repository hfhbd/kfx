package io.github.hfhbd.kfx.wsdl

import kotlinx.serialization.modules.SerializersModule

interface WsdlTransformerFactory {
    fun create(): WsdlTransformer
    fun serializerModule(): SerializersModule
}
