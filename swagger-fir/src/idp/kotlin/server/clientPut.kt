package server

import Client
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

public fun Route.clientPut(action: suspend ApplicationCall.(Client) -> Unit) {
  route(path = """/api/v1/instances/{serviceInstanceID}/clients/{realm}/{clientName}""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<Client>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
