package server

import ClientStatistics
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route

public fun Route.clientStatistics(action: suspend ApplicationCall.() -> ClientStatistics) {
  route(path = """/api/v1/instances/{serviceInstanceID}/clients/{realm}/{clientName}/statistics""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
