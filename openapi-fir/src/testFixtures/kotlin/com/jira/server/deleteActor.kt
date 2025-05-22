package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete actors from project role
 * Deletes actors (users or groups) from a project role.
 */
public fun Route.deleteActor(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/project/{projectIdOrKey}/role/{id}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
