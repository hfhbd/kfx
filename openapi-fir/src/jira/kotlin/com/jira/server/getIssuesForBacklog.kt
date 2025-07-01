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
 * Get all issues from the board's backlog
 * Returns all issues from a board's backlog, for a given board Id.
 */
public fun Route.getIssuesForBacklog(action: suspend ApplicationCall.() -> IssueBean) {
  route(path = """/agile/1.0/board/{boardId}/backlog""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
