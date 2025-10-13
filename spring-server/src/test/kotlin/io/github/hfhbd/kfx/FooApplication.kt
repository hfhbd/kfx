package io.github.hfhbd.kfx

import kotlinx.serialization.json.Json
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter

@SpringBootApplication
class FooApplication {
    @Bean
    fun messageConverter() = KotlinSerializationJsonHttpMessageConverter(Json {
        ignoreUnknownKeys = true
    })
}
