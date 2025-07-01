package com.jira.server

import com.jira.WorklogWithPaginationBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get worklogs for an issue
 * Returns all work logs for an issue. Work logs won't be returned if the Log work field is hidden for the project.
 */
public fun Route.getIssueWorklog(action: suspend ApplicationCall.() -> WorklogWithPaginationBean) {
  route(path = """/api/2/issue/{issueIdOrKey}/worklog""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
