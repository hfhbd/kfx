package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete a priority scheme
 * Deletes a priority scheme. All projects using deleted scheme will use default priority scheme afterwards.
 */
public fun Route.deletePriorityScheme(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/priorityschemes/{schemeId}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
