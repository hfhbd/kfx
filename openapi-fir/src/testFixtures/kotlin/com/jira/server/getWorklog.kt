package com.jira.server

import com.jira.Worklog
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get a worklog by id
 * Returns a specific worklog. The work log won't be returned if the Log work field is hidden for the project.
 */
public fun Route.getWorklog(action: suspend ApplicationCall.() -> Worklog) {
  route(path = """/api/2/issue/{issueIdOrKey}/worklog/{id}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
