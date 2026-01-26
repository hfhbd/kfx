package server

import ServiceInstanceProvisionRequest
import ServiceInstanceProvisionResponse
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route

public fun Route.serviceInstanceProvision(action: suspend ApplicationCall.(ServiceInstanceProvisionRequest) -> ServiceInstanceProvisionResponse) {
  route(path = """/v2/service_instances/{instance_id}""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<ServiceInstanceProvisionRequest>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
