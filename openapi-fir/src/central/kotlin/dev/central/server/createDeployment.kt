package dev.central.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Publish a deployment. Deployments can be published if they are in a `VALIDATED` state.
 */
public fun Route.createDeployment(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/v1/publisher/deployment/{deploymentId}""") {
    post {
      call.action()
      call.respond(NoContent)
    }
  }
}
