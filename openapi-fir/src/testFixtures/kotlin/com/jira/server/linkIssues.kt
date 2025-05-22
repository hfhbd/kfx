package com.jira.server

import com.jira.LinkIssueRequestJsonBean
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
 * Create an issue link between two issues
 * Creates an issue link between two issues.
 */
public fun Route.linkIssues(action: suspend ApplicationCall.(LinkIssueRequestJsonBean) -> Unit) {
  route(path = """/api/2/issueLink""") {
    contentType(Json) {
      post {
        val body = call.receive<LinkIssueRequestJsonBean>()
        call.action(body)
        call.respond(Created)
      }
    }
  }
}
