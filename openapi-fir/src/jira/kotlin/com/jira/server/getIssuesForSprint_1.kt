package com.jira.server

import com.jira.SearchResultsBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all issues in a sprint
 * Returns all issues in a sprint, for a given sprint Id. This only includes issues that the user has permission to view. By default, the returned issues are ordered by rank.
 */
public fun Route.getIssuesForSprint_1(action: suspend ApplicationCall.() -> SearchResultsBean) {
  route(path = """/agile/1.0/sprint/{sprintId}/issue""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
