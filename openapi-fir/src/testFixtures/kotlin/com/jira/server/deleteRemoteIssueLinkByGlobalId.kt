package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete remote issue link
 * Delete the remote issue link with the given global id on the issue.
 */
public fun Route.deleteRemoteIssueLinkByGlobalId(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/issue/{issueIdOrKey}/remotelink""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
