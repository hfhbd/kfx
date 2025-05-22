package com.jira.server

import com.jira.WorkflowSchemeBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Create a draft for a workflow scheme
 * Create a draft for the passed scheme. The draft will be a copy of the state of the parent.
 */
public fun Route.createDraftForParent(action: suspend ApplicationCall.() -> WorkflowSchemeBean) {
  route(path = """/api/2/workflowscheme/{id}/createdraft""") {
    accept(Json) {
      post {
        val response = call.action()
        call.response.status(Created)
        call.respond(response)
      }
    }
  }
}
