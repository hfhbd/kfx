package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Get validation for user anonymization
 * Validates user anonymization process.
 */
public fun Route.validateUserAnonymization(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/user/anonymization""") {
    `get` {
      call.action()
      call.respond(OK)
    }
  }
}
