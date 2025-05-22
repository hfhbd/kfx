package com.jira.server

import com.jira.IssueBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get a single issue with Agile fields
 * Returns a single issue, for a given issue Id or issue key. Issues returned from this resource include Agile fields, like sprint, closedSprints, flagged, and epic.
 */
public fun Route.getIssue_1(action: suspend ApplicationCall.() -> IssueBean) {
  route(path = """/agile/1.0/issue/{issueIdOrKey}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
