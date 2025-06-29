package com.jira.server

import com.jira.IssueTypeMappingBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get issue type mapping for a scheme
 * Returns the issue type mapping for the passed workflow scheme.
 */
public fun Route.getIssueType(action: suspend ApplicationCall.() -> IssueTypeMappingBean) {
  route(path = """/api/2/workflowscheme/{id}/issuetype/{issueType}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
