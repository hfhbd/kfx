package io.github.hfhbd.kfx

import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.coRouter
import server.bazA
import server.bazACsrfToken

@RestController
class FooController {
    @Bean
    fun router() = coRouter {
        bazA {
            "bazA"
        }
        bazACsrfToken {
            error("token error")
        }
    }
}
