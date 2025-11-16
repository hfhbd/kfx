package server

import kotlin.Unit
import org.springframework.http.HttpMethod.HEAD
import org.springframework.web.reactive.function.server.CoRouterFunctionDsl
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.buildAndAwait

/**
 * Get the CSRF Token for BazA
 */
public fun CoRouterFunctionDsl.bazACsrfToken(action: suspend ServerRequest.() -> Unit) {
  path(pattern = """/http/foo/bar/baz""").nest {
    method(HEAD) { request ->
      request.action()
      ok().buildAndAwait()
    }
  }
}
