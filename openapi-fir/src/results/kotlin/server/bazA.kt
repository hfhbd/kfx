package server

import com.example.FooInput
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.`header`
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import results.BazAResult

/**
 * Foo Bar API
 */
public fun Route.bazA(action: suspend ApplicationCall.(FooInput) -> BazAResult) {
  route(path = """/http/foo/bar/baz""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<FooInput>()
          val response = call.action(body)
          when (response) {
            is BazAResult.Success -> {
              if (response.logid != null) {
                call.response.`header`("logid", response.logid)
              }
              if (call.response.status() == null) {
                call.response.status(OK)
              }
              call.respond(response.body)
            }
            is BazAResult.Failure -> {
              if (response.logid != null) {
                call.response.`header`("logid", response.logid)
              }
              if (call.response.status() == null) {
                call.response.status(InternalServerError)
              }
              call.respond(response.body)
            }
          }
        }
      }
    }
  }
}
