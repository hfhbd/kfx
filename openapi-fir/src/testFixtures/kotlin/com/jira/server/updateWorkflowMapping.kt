package com.jira.server

import com.jira.WorkflowMappingBean
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
 * Update a workflow mapping in a scheme
 * Update the scheme to include the passed mapping. The body is a representation of the workflow mapping. Values not passed are assumed to indicate no change for that field.
 * The passed representation can have its updateDraftIfNeeded flag set to true to indicate that the draft
 * should be created/updated when the actual scheme cannot be edited.
 */
public fun Route.updateWorkflowMapping(action: suspend ApplicationCall.(WorkflowMappingBean) -> WorkflowSchemeBean) {
  route(path = """/api/2/workflowscheme/{id}/workflow""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<WorkflowMappingBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
