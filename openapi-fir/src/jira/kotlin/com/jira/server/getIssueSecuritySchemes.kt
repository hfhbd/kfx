package com.jira.server

import com.jira.SecuritySchemesJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all issue security schemes
 * Returns all issue security schemes that are defined.
 */
public fun Route.getIssueSecuritySchemes(action: suspend ApplicationCall.() -> SecuritySchemesJsonBean) {
  route(path = """/api/2/issuesecurityschemes""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
