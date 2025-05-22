package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete a sprint
 * Deletes a sprint. Once a sprint is deleted, all issues in the sprint will be moved to the backlog. To delete a synced sprint, you must unsync it first.
 */
public fun Route.deleteSprint(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/agile/1.0/sprint/{sprintId}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
