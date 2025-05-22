package io.github.hfhbd.kfx.openapi

fun interface OpenApiTransformer {
    operator fun invoke(openApi: OpenApi): OpenApi
}
