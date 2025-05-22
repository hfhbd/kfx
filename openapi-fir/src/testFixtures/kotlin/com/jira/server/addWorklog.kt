package com.jira.server

import com.jira.Worklog
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Add a worklog entry
 * Adds a new worklog entry to an issue.
 */
public fun Route.addWorklog(action: suspend ApplicationCall.(Worklog) -> Worklog) {
  route(path = """/api/2/issue/{issueIdOrKey}/worklog""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<Worklog>()
          val response = call.action(body)
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
