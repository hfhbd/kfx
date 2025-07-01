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
 * Delete a workflow mapping from a scheme
 * Delete the passed workflow from the workflow scheme.
 */
public fun Route.deleteWorkflowMapping(action: suspend ApplicationCall.() -> WorkflowSchemeBean) {
  route(path = """/api/2/workflowscheme/{id}/workflow""") {
    accept(Json) {
      delete {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
