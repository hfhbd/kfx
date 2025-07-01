package com.jira.server

import com.jira.IssueTypeJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get list of all issue types visible to user
 * Returns a list of all issue types visible to the user
 */
public fun Route.getIssueAllTypes(action: suspend ApplicationCall.() -> IssueTypeJsonBean) {
  route(path = """/api/2/issuetype""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
