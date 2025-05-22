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
 * Get full representation of issue type by id
 * Returns a full representation of the issue type that has the given id.
 */
public fun Route.getIssueType_1(action: suspend ApplicationCall.() -> IssueTypeJsonBean) {
  route(path = """/api/2/issuetype/{id}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
