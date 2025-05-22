package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Move tab position
 * Moves tab position.
 */
public fun Route.moveTab(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/screens/{screenId}/tabs/{tabId}/move/{pos}""") {
    post {
      call.action()
      call.respond(NoContent)
    }
  }
}
