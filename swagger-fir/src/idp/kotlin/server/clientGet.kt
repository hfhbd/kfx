package server

import Client
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.route

public fun Route.clientGet(action: suspend ApplicationCall.() -> Client) {
  route(path = """/api/v1/instances/{serviceInstanceID}/clients/{realm}/{clientName}""") {
    contentType(Json) {
      accept(Json) {
        `get` {
          val response = call.action()
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
