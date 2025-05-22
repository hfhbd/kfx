package com.jira.server

import com.jira.CreateMetaIssueTypeBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get metadata for project issue types
 * Returns the metadata for issue types used for creating issues. Data will not be returned if the user does not have permission to create issues in that project.
 */
public fun Route.getCreateIssueMetaProjectIssueTypes(action: suspend ApplicationCall.() -> CreateMetaIssueTypeBean) {
  route(path = """/api/2/issue/createmeta/{projectIdOrKey}/issuetypes""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
