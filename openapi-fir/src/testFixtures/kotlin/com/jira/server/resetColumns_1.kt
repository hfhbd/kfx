package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Reset columns for filter
 * Resets the columns for the given filter such that the filter no longer has its own column config
 */
public fun Route.resetColumns_1(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/filter/{id}/columns""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
