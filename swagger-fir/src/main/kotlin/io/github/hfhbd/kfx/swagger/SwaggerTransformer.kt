package io.github.hfhbd.kfx.swagger

fun interface SwaggerTransformer {
    operator fun invoke(definitions: Swagger): Swagger
}
