package server

import com.example.FooInput
import kotlin.String
import org.springframework.http.HttpMethod.DELETE
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.CoRouterFunctionDsl
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

/**
 * Using 2XX and 4XX
 */
public fun CoRouterFunctionDsl.deleteHttpFooBarBaz(action: suspend ServerRequest.(input: FooInput) -> String) {
  path(pattern = """/http/foo/bar/baz""").nest {
    contentType(APPLICATION_JSON).nest {
      accept(APPLICATION_JSON).nest {
        method(DELETE) { request ->
          val body = request.awaitBody<FooInput>()
          val response = request.action(body)
          ok().bodyValueAndAwait(response)
        }
      }
    }
  }
}
