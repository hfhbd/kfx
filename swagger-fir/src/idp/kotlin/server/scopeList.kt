package server

import Scope
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route
import kotlin.collections.List

public fun Route.scopeList(action: suspend ApplicationCall.() -> List<Scope>) {
  route(path = """/api/v1/instances/{serviceInstanceID}/scopes""") {
    accept(Json) {
      `get` {
        val response = call.action()
        if (call.response.status() == null) {
          call.response.status(OK)
        }
        call.respond(response)
      }
    }
  }
}
