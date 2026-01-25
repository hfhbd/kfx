package server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

public fun Route.clientRegenerateSecret(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/v1/instances/{serviceInstanceID}/clients/{realm}/{clientName}/regenerate-secret""") {
    put {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
