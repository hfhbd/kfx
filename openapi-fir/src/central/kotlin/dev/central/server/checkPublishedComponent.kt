package dev.central.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Check whether a component is published.
 */
public fun Route.checkPublishedComponent(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/v1/publisher/published""") {
    accept(Json) {
      `get` {
        call.action()
        call.respond(OK)
      }
    }
  }
}
