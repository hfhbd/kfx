package io.github.hfhbd.kfx.swagger

import io.github.hfhbd.kfx.ir.IRTree

fun interface SwaggerTransformer {
    operator fun invoke(definitions: Swagger, irTree: IRTree): IRTree
}
