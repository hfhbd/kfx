package server

import com.example.FooInput
import kotlin.String
import org.springframework.http.HttpMethod.POST
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.CoRouterFunctionDsl
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

/**
 * Foo Bar API
 */
public fun CoRouterFunctionDsl.bazA(action: suspend ServerRequest.(input: FooInput) -> String) {
  path(pattern = """/http/foo/bar/baz""").nest {
    contentType(APPLICATION_JSON).nest {
      accept(APPLICATION_JSON).nest {
        method(POST) { request ->
          val body = request.awaitBody<FooInput>()
          val response = request.action(body)
          ok().bodyValueAndAwait(response)
        }
      }
    }
  }
}
