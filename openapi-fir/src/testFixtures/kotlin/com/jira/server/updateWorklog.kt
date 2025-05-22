package com.jira.server

import com.jira.Worklog
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route

/**
 * Update a worklog entry
 * Updates an existing worklog entry. Note that:
 * - Fields possible for editing are: comment, visibility, started, timeSpent and timeSpentSeconds.
 * - Either timeSpent or timeSpentSeconds can be set.
 * - Fields which are not set will not be updated.
 * - For a request to be valid, it has to have at least one field change.
 */
public fun Route.updateWorklog(action: suspend ApplicationCall.(Worklog) -> Worklog) {
  route(path = """/api/2/issue/{issueIdOrKey}/worklog/{id}""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<Worklog>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
