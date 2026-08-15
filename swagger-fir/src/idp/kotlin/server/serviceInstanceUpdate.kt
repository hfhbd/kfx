package server

import ServiceInstanceUpdateRequest
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.patch
import io.ktor.server.routing.route
import kotlin.Unit

public fun Route.serviceInstanceUpdate(action: suspend ApplicationCall.(ServiceInstanceUpdateRequest) -> Unit) {
  route(path = """/v2/service_instances/{instance_id}""") {
    contentType(Json) {
      accept(Json) {
        patch {
          val body = call.receive<ServiceInstanceUpdateRequest>()
          val response = call.action(body)
          if (call.response.status() == null) {
            call.response.status(OK)
          }
          call.respond(response)
        }
      }
    }
  }
}
