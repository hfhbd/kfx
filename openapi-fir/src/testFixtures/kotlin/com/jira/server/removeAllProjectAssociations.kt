package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Remove all project associations for specified scheme
 * Removes all project associations for the specified issue type scheme
 */
public fun Route.removeAllProjectAssociations(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/issuetypescheme/{schemeId}/associations""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
