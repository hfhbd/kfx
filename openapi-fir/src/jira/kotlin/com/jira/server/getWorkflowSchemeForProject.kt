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
 * Get workflow scheme for project
 * Returns the workflow scheme that is associated with requested project.
 */
public fun Route.getWorkflowSchemeForProject(action: suspend ApplicationCall.() -> WorkflowSchemeBean) {
  route(path = """/api/2/project/{projectKeyOrId}/workflowscheme""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
