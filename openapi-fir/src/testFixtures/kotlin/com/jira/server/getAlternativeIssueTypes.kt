package com.jira.server

import com.jira.IssueTypeJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get list of alternative issue types for given id
 * Returns a list of all alternative issue types for the given issue type id.
 */
public fun Route.getAlternativeIssueTypes(action: suspend ApplicationCall.() -> IssueTypeJsonBean) {
  route(path = """/api/2/issuetype/{id}/alternatives""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
