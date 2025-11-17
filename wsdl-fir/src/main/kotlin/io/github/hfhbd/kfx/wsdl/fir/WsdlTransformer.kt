package io.github.hfhbd.kfx.wsdl.fir

import io.github.hfhbd.kfx.xsd.model.Schema

interface WsdlTransformer {
    operator fun invoke(schema: Schema, targetNamespace: String): Schema
}
