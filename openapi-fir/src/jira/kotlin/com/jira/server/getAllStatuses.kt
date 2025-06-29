package com.jira.server

import com.jira.IssueTypeWithStatusJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all issue types with statuses for a project
 * Get all issue types with valid status values for a project
 */
public fun Route.getAllStatuses(action: suspend ApplicationCall.() -> IssueTypeWithStatusJsonBean) {
  route(path = """/api/2/project/{projectIdOrKey}/statuses""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
