package com.jira.server

import com.jira.SecurityListLevelJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all security levels for project
 * Returns all security levels for the project that the current logged in user has access to. If the user does not have the Set Issue Security permission, the list will be empty.
 */
public fun Route.getSecurityLevelsForProject(action: suspend ApplicationCall.() -> SecurityListLevelJsonBean) {
  route(path = """/api/2/project/{projectKeyOrId}/securitylevel""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
