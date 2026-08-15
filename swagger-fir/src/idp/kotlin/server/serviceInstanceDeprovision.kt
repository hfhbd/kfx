package server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

public fun Route.serviceInstanceDeprovision(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/v2/service_instances/{instance_id}""") {
    accept(Json) {
      delete {
        val response = call.action()
        if (call.response.status() == null) {
          call.response.status(OK)
        }
        call.respond(response)
      }
    }
  }
}
