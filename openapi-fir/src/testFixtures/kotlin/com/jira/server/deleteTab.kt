package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete a tab from a screen
 * Deletes tab from given screen.
 */
public fun Route.deleteTab(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/screens/{screenId}/tabs/{tabId}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
