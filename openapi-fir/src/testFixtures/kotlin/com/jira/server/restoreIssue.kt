package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Restore an archived issue
 * Restores an archived issue.
 */
public fun Route.restoreIssue(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/issue/{issueIdOrKey}/restore""") {
    put {
      call.action()
      call.respond(NoContent)
    }
  }
}
