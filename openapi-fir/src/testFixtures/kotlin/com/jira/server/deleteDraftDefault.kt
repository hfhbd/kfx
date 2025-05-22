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
 * Remove default workflow from a draft scheme
 * Remove the default workflow from the passed draft workflow scheme.
 */
public fun Route.deleteDraftDefault(action: suspend ApplicationCall.() -> WorkflowSchemeBean) {
  route(path = """/api/2/workflowscheme/{id}/draft/default""") {
    accept(Json) {
      delete {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
