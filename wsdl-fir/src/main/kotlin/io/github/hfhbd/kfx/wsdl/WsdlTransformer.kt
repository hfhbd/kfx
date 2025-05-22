package io.github.hfhbd.kfx.wsdl

interface WsdlTransformer {
    operator fun invoke(definitions: WSDL): WSDL
    operator fun invoke(schema: Schema): Schema
}
