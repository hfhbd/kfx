package com.jira.server

import com.jira.WorkflowSchemeBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.delete
import io.ktor.server.routing.route

/**
 * Delete an issue type mapping from a draft scheme
 * Remove the specified issue type mapping from the draft scheme.
 */
public fun Route.deleteDraftIssueType(action: suspend ApplicationCall.() -> WorkflowSchemeBean) {
  route(path = """/api/2/workflowscheme/{id}/draft/issuetype/{issueType}""") {
    accept(Json) {
      delete {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
