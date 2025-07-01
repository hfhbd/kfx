package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete remote issue link by id
 * Delete the remote issue link with the given id on the issue.
 */
public fun Route.deleteRemoteIssueLinkById(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/issue/{issueIdOrKey}/remotelink/{linkId}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
