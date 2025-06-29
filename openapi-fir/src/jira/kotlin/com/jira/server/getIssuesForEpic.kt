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
 * Get all issues for a specific epic
 * Returns all issues that belong to an epic on the board, for the given epic Id and the board Id.
 */
public fun Route.getIssuesForEpic(action: suspend ApplicationCall.() -> IssueBean) {
  route(path = """/agile/1.0/board/{boardId}/epic/{epicId}/issue""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
