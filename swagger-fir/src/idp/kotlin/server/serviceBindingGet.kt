package server

import ServiceBindingResource
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route

public fun Route.serviceBindingGet(action: suspend ApplicationCall.() -> ServiceBindingResource) {
  route(path = """/v2/service_instances/{instance_id}/service_bindings/{binding_id}""") {
    `get` {
      val response = call.action()
      call.response.status(OK)
      call.respond(response)
    }
  }
}
