package server

import Scope
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

public fun Route.scopePut(action: suspend ApplicationCall.(Scope) -> Unit) {
  route(path = """/api/v1/instances/{serviceInstanceID}/scopes/{realm}/{scopeName}""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<Scope>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
