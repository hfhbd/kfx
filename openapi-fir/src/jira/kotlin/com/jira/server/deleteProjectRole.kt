package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Deletes a role
 * Deletes a role. May return 403 in the future
 */
public fun Route.deleteProjectRole(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/role/{id}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
