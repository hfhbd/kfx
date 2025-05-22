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
 * Update a specified workflow scheme
 * Update the passed workflow scheme. The body of the request is a representation of the workflow scheme. Values not passed are assumed to indicate no change for that field.
 * The passed representation can have its updateDraftIfNeeded flag set to true to indicate that the draft
 * should be created and/or updated when the actual scheme cannot be edited (e.g. when the scheme is being used by
 * a project). Values not appearing the body will not be touched.
 */
public fun Route.update(action: suspend ApplicationCall.(WorkflowSchemeBean) -> WorkflowSchemeBean) {
  route(path = """/api/2/workflowscheme/{id}""") {
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
