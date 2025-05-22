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
 * Get issues for a specific epic
 * Returns all issues that belong to the epic, for the given epic Id. This only includes issues that the user has permission to view. Issues returned from this resource include Agile fields, like sprint, closedSprints, flagged, and epic. By default, the returned issues are ordered by rank.
 */
public fun Route.getIssuesForEpic_1(action: suspend ApplicationCall.() -> SearchResultsBean) {
  route(path = """/agile/1.0/epic/{epicIdOrKey}/issue""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
