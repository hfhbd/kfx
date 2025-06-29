package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Update 'showWhenEmptyIndicator' for a field
 * Update 'showWhenEmptyIndicator' for given field on screen.
 */
public fun Route.updateShowWhenEmptyIndicator(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/screens/{screenId}/tabs/{tabId}/fields/{id}/updateShowWhenEmptyIndicator/{newValue}""") {
    put {
      call.action()
      call.respond(NoContent)
    }
  }
}
