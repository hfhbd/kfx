package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Get all roles in project
 * Returns all roles in the given project Id or key, with links to full details on each role.
 */
public fun Route.getProjectRoles(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/project/{projectIdOrKey}/role""") {
    `get` {
      call.action()
      call.respond(OK)
    }
  }
}
