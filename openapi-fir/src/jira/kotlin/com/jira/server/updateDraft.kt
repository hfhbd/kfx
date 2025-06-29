package com.jira.server

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
 * Update a draft workflow scheme
 * Update a draft workflow scheme. The draft will created if necessary. The body of the request is a representation of the workflow scheme. Values not passed are assumed to indicate no change for that field.
 */
public fun Route.updateDraft(action: suspend ApplicationCall.(WorkflowSchemeBean) -> WorkflowSchemeBean) {
  route(path = """/api/2/workflowscheme/{id}/draft""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<WorkflowSchemeBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
