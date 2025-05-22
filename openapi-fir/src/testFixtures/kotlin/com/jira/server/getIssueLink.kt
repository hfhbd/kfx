package com.jira.server

import com.jira.IssueLinks
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get an issue link with the specified id
 * Returns an issue link with the specified id.
 */
public fun Route.getIssueLink(action: suspend ApplicationCall.() -> IssueLinks) {
  route(path = """/api/2/issueLink/{linkId}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
