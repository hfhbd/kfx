package dev.central.server

import dev.central.CheckStatus
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Retrieve status of a deployment. Polling this endpoint can be useful for determining when a deployment changes state.
 */
public fun Route.checkStatus(action: suspend ApplicationCall.() -> CheckStatus) {
  route(path = """/api/v1/publisher/status""") {
    accept(Json) {
      post {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
