package com.jira.server

import com.jira.IssueTypeMappingBean
import com.jira.WorkflowSchemeBean
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
 * Set an issue type mapping for a scheme
 * Set the issue type mapping for the passed scheme. The passed representation can have its updateDraftIfNeeded flag set to true to indicate that
 * the draft should be created/updated when the actual scheme cannot be edited.
 */
public fun Route.setIssueType(action: suspend ApplicationCall.(IssueTypeMappingBean) -> WorkflowSchemeBean) {
  route(path = """/api/2/workflowscheme/{id}/issuetype/{issueType}""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<IssueTypeMappingBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
