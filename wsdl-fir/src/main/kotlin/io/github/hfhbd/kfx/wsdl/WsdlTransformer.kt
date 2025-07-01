package io.github.hfhbd.kfx.wsdl

import io.github.hfhbd.kfx.ir.IRTree

fun interface WsdlTransformer {
    operator fun invoke(definitions: WSDL, irTree: IRTree): IRTree
}
