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
 * Update default workflow for a scheme
 * Set the default workflow for the passed workflow scheme. The passed representation can have its
 * updateDraftIfNeeded flag set to true to indicate that the draft should be created/updated when the actual scheme
 * cannot be edited.
 */
public fun Route.updateDefault(action: suspend ApplicationCall.(DefaultBean) -> WorkflowSchemeBean) {
  route(path = """/api/2/workflowscheme/{id}/default""") {
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
