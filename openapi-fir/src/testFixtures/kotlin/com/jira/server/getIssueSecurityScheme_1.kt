package com.jira.server

import com.jira.SecuritySchemeJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get issue security scheme for project
 * Returns the issue security scheme for project.
 */
public fun Route.getIssueSecurityScheme_1(action: suspend ApplicationCall.() -> SecuritySchemeJsonBean) {
  route(path = """/api/2/project/{projectKeyOrId}/issuesecuritylevelscheme""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
