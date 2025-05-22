package server

import dev.example.FooSealed
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.String

/**
 * Foo Bar API
 */
public fun Route.createSealed(action: suspend ApplicationCall.(FooSealed) -> String) {
  route(path = """/sealed""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<FooSealed>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
