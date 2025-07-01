package com.jira.server

import com.jira.IssuePickerResult
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get suggested issues for auto-completion
 * Get issue picker resource
 */
public fun Route.getIssuePickerResource(action: suspend ApplicationCall.() -> IssuePickerResult) {
  route(path = """/api/2/issue/picker""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
