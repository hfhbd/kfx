package com.jira.server

import com.jira.IssueTypeSchemeListBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get list of all issue type schemes visible to user
 * Returns a list of all issue type schemes visible to the user. All issue types associated with the scheme will only be returned if an additional query parameter is provided: expand=schemes.issueTypes. Similarly, the default issue type associated with the scheme (if one exists) will only be returned if an additional query parameter is provided: expand=schemes.defaultIssueType. Note that both query parameters can be used together: expand=schemes.issueTypes,schemes.defaultIssueType.
 */
public fun Route.getAllIssueTypeSchemes(action: suspend ApplicationCall.() -> IssueTypeSchemeListBean) {
  route(path = """/api/2/issuetypescheme""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
