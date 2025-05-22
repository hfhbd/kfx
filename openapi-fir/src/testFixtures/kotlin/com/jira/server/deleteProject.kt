package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete a project
 * Deletes a project
 */
public fun Route.deleteProject(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/project/{projectIdOrKey}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
