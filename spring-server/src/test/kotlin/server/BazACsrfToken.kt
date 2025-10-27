package server

import org.springframework.web.reactive.function.server.CoRouterFunctionDsl
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.buildAndAwait

/**
 * Get the CSRF Token for BazA
 */
fun CoRouterFunctionDsl.bazACsrfToken(action: suspend ServerRequest.() -> Unit) {
  HEAD(pattern = """/http/foo/bar/baz""") { request ->
    request.action()
    ok().buildAndAwait()
  }
}
