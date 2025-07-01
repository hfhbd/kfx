package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Get user anonymization progress
 * Returns information about a user anonymization operation progress.
 */
public fun Route.getProgress_1(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/user/anonymization/progress""") {
    `get` {
      call.action()
      call.respond(OK)
    }
  }
}
