package com.jira.server

import com.jira.RemoteIssueLinkBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get remote issue links for an issue
 * Get remote issue links for an issue.
 */
public fun Route.getRemoteIssueLinks(action: suspend ApplicationCall.() -> RemoteIssueLinkBean) {
  route(path = """/api/2/issue/{issueIdOrKey}/remotelink""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
