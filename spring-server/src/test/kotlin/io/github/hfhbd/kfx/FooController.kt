package io.github.hfhbd.kfx

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.coRouter
import org.springframework.web.reactive.function.server.contentTypeOrNull
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
        contentType(MediaType.APPLICATION_JSON).nest {
            headers { it.firstHeader("SOAPAction") == "f" }.nest {
                method(HttpMethod.POST) {
                    ok().buildAndAwait()
                }
            }
        }
        contentType(MediaType.APPLICATION_JSON).nest {
            headers { it.contentTypeOrNull()?.getParameter("action") == "f" }.nest {
                method(HttpMethod.HEAD) {
                    ok().buildAndAwait()
                }
            }
        }
    }
}
