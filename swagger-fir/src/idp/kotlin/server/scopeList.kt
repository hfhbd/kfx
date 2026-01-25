package server

import Scope
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route
import kotlin.collections.List

public fun Route.scopeList(action: suspend ApplicationCall.() -> List<Scope>) {
  route(path = """/api/v1/instances/{serviceInstanceID}/scopes""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
