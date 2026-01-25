package server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

public fun Route.clientDelete(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/v1/instances/{serviceInstanceID}/clients/{realm}/{clientName}""") {
    delete {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
