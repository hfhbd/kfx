package io.github.hfhbd.kfx.openapi

import io.github.hfhbd.kfx.ir.IRTree

fun interface OpenApiTransformer {
    operator fun invoke(openApi: OpenApi, irTree: IRTree): IRTree
}
