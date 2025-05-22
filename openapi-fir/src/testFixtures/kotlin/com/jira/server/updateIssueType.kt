package com.jira.server

import com.jira.IssueTypeUpdateBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Update specified issue type from JSON representation
 * Updates the specified issue type from a JSON representation.
 */
public fun Route.updateIssueType(action: suspend ApplicationCall.(IssueTypeUpdateBean) -> Unit) {
  route(path = """/api/2/issuetype/{id}""") {
    contentType(Json) {
      put {
        val body = call.receive<IssueTypeUpdateBean>()
        call.action(body)
        call.respond(OK)
      }
    }
  }
}
