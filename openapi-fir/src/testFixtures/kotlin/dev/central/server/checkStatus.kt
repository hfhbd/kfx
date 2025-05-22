package dev.central.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Retrieve status of a deployment. Polling this endpoint can be useful for determining when a deployment changes state.
 */
public fun Route.checkStatus(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/v1/publisher/status""") {
    accept(Json) {
      post {
        call.action()
        call.respond(OK)
      }
    }
  }
}
