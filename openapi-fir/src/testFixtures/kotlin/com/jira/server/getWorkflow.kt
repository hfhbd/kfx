package com.jira.server

import com.jira.WorkflowSchemeBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get workflow mappings for a scheme
 * Returns the workflow mappings or requested mapping to the caller for the passed scheme.
 */
public fun Route.getWorkflow(action: suspend ApplicationCall.() -> WorkflowSchemeBean) {
  route(path = """/api/2/workflowscheme/{id}/workflow""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
