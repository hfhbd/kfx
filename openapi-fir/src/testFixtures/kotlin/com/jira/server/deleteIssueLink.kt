package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete an issue link with the specified id
 * Deletes an issue link with the specified id.
 */
public fun Route.deleteIssueLink(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/issueLink/{linkId}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
