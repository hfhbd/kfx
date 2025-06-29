package com.jira.server

import com.jira.IssueTypeSchemeBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get full representation of issue type scheme by id
 * Returns a full representation of the issue type scheme that has the given id
 */
public fun Route.getIssueTypeScheme(action: suspend ApplicationCall.() -> IssueTypeSchemeBean) {
  route(path = """/api/2/issuetypescheme/{schemeId}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
