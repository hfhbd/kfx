package com.jira.server

import com.jira.IssueRefJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get an issue's subtask list
 * Returns an issue's subtask list
 */
public fun Route.getSubTasks(action: suspend ApplicationCall.() -> IssueRefJsonBean) {
  route(path = """/api/2/issue/{issueIdOrKey}/subtask""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
