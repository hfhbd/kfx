package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete specified issue type scheme
 * Deletes the specified issue type scheme. Any projects associated with this IssueTypeScheme will be automatically associated with the global default IssueTypeScheme.
 */
public fun Route.deleteIssueTypeScheme(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/issuetypescheme/{schemeId}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
