package io.github.hfhbd.kfx

import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.coRouter
import server.bazACsrfToken

@RestController
class BazATokenController {
    @Bean
    fun bazACsrfTokenRouter() = coRouter {
        bazACsrfToken {
            error("token error")
        }
    }
}
