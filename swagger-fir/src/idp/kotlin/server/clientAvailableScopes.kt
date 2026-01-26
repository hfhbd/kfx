package server

import AvailableScopeListEntry
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.route
import kotlin.collections.List

public fun Route.clientAvailableScopes(action: suspend ApplicationCall.() -> List<AvailableScopeListEntry>) {
  route(path = """/api/v1/instances/{serviceInstanceID}/clients/{realm}/{clientName}/available-scopes""") {
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
