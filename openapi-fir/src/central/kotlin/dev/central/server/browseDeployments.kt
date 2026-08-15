package dev.central.server

import dev.central.BrowseDeploymentsRequest
import dev.central.BrowseDeploymentsResponse
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Browse the content of the deployment.
 */
public fun Route.browseDeployments(action: suspend ApplicationCall.(BrowseDeploymentsRequest) -> BrowseDeploymentsResponse) {
  route(path = """/api/v1/publisher/deployments/files""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<BrowseDeploymentsRequest>()
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
