package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Get available field screens
 * Adds field or custom field to the default tab.
 */
public fun Route.getAllScreens(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/screens""") {
    `get` {
      call.action()
      call.respond(Created)
    }
  }
}
