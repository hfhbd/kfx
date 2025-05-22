package com.jira.server

import com.jira.DefaultBean
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
 * Update default workflow for a draft scheme
 * Set the default workflow for the passed draft workflow scheme.
 */
public fun Route.updateDraftDefault(action: suspend ApplicationCall.(DefaultBean) -> WorkflowSchemeBean) {
  route(path = """/api/2/workflowscheme/{id}/draft/default""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<DefaultBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
