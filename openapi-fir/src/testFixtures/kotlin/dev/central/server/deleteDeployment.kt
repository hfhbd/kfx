package dev.central.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Drop a deployment. Deployments can be dropped if they are in a `FAILED` or `VALIDATED` state.
 */
public fun Route.deleteDeployment(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/v1/publisher/deployment/{deploymentId}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
