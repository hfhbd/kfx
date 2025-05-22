package com.jira.server

import com.jira.IssueTypeCreateBean
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
 * Create an issue type from JSON representation
 * Creates an issue type from a JSON representation and adds the issue newly created issue type to the default issue type scheme.
 */
public fun Route.createIssueType(action: suspend ApplicationCall.(IssueTypeCreateBean) -> Unit) {
  route(path = """/api/2/issuetype""") {
    contentType(Json) {
      post {
        val body = call.receive<IssueTypeCreateBean>()
        call.action(body)
        call.respond(Created)
      }
    }
  }
}
