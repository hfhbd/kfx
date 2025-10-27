package server

import com.example.FooInput
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import kotlin.String
import org.springframework.web.reactive.function.server.CoRouterFunctionDsl
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

/**
 * Foo Bar API
 */
fun CoRouterFunctionDsl.bazA(action: suspend ServerRequest.(FooInput) -> String) {
  path(pattern = """/http/foo/bar/baz""").nest {
    contentType(MediaType.APPLICATION_JSON).nest {
      accept(MediaType.APPLICATION_JSON).nest {
        method(HttpMethod.POST) { request ->
          val body = request.awaitBody<FooInput>()
          val response = request.action(body)

          ok().bodyValueAndAwait(response)
        }
      }
    }
  }
}
