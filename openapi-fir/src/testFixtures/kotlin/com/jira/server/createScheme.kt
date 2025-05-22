package com.jira.server

import com.jira.WorkflowSchemeBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Create a new workflow scheme
 * Create a new workflow scheme. The body contains a representation of the new scheme. Values not passed are assumed to be set to their defaults.
 */
public fun Route.createScheme(action: suspend ApplicationCall.(WorkflowSchemeBean) -> Unit) {
  route(path = """/api/2/workflowscheme""") {
    contentType(Json) {
      post {
        val body = call.receive<WorkflowSchemeBean>()
        call.action(body)
        call.respond(Created)
      }
    }
  }
}
