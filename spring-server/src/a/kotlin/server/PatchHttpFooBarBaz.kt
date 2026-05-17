package server

import com.example.FooInput
import java.net.URI
import kotlin.Pair
import kotlin.String
import org.springframework.http.HttpMethod.PATCH
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.CoRouterFunctionDsl
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

/**
 * Foo Bar API
 */
public fun CoRouterFunctionDsl.patchHttpFooBarBaz(action: suspend ServerRequest.(input: FooInput) -> Pair<String, URI>) {
  path(pattern = """/http/foo/bar/baz""").nest {
    contentType(APPLICATION_JSON).nest {
      accept(APPLICATION_JSON).nest {
        method(PATCH) { request ->
          val body = request.awaitBody<FooInput>()
          val (response, location) = request.action(body)
          created(location).bodyValueAndAwait(response)
        }
      }
    }
  }
}
