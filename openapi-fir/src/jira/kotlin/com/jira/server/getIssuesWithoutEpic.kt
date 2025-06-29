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
 * Get all issues without an epic
 * Returns all issues that do not belong to any epic on a board, for a given board Id.
 */
public fun Route.getIssuesWithoutEpic(action: suspend ApplicationCall.() -> IssueBean) {
  route(path = """/agile/1.0/board/{boardId}/epic/none/issue""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
