package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete an issue
 * Deletes an issue. If the issue has subtasks you must set the parameter deleteSubtasks=true to delete the issue. You cannot delete an issue without its subtasks also being deleted.
 */
public fun Route.deleteIssue(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/issue/{issueIdOrKey}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
