package io.github.hfhbd.kfx.openapi.fir

import io.github.hfhbd.kfx.ir.IRTree
import io.github.hfhbd.kfx.openapi.model.OpenApi

fun interface OpenApiTransformer {
    operator fun invoke(openApi: OpenApi, irTree: IRTree): IRTree
}
