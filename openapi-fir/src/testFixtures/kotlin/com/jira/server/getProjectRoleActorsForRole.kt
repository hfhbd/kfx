package com.jira.server

import com.jira.ProjectRoleActorsBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get default actors for a role
 * Gets default actors for the given role.
 */
public fun Route.getProjectRoleActorsForRole(action: suspend ApplicationCall.() -> ProjectRoleActorsBean) {
  route(path = """/api/2/role/{id}/actors""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
