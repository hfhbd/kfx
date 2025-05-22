package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete project category
 * Delete a project category.
 */
public fun Route.removeProjectCategory(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/projectCategory/{id}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
