package com.jira.server

import com.jira.ProjectRoleBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get details for a project role
 * Returns the details for a given project role in a project.
 */
public fun Route.getProjectRole(action: suspend ApplicationCall.() -> ProjectRoleBean) {
  route(path = """/api/2/project/{projectIdOrKey}/role/{id}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
