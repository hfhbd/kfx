package io.github.hfhbd.kfx.swagger.fir

import io.github.hfhbd.kfx.ir.IRTree
import io.github.hfhbd.kfx.swagger.model.Swagger

fun interface SwaggerTransformer {
    operator fun invoke(definitions: Swagger, irTree: IRTree): IRTree
}
