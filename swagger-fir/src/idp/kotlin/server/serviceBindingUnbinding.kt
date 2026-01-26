package server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

public fun Route.serviceBindingUnbinding(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/v2/service_instances/{instance_id}/service_bindings/{binding_id}""") {
    contentType(Json) {
      accept(Json) {
        delete {
          val response = call.action()
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
