package com.jira.server

import com.jira.RemoteIssueLinkBean
import com.jira.RemoteIssueLinkCreateOrUpdateRequest
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Create or update remote issue link
 * Creates or updates a remote issue link from a JSON representation. If a globalId is provided and a remote issue link exists with that globalId, the remote issue link is updated. Otherwise, the remote issue link is created.
 */
public fun Route.createOrUpdateRemoteIssueLink(action: suspend ApplicationCall.(RemoteIssueLinkCreateOrUpdateRequest) -> RemoteIssueLinkBean) {
  route(path = """/api/2/issue/{issueIdOrKey}/remotelink""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<RemoteIssueLinkCreateOrUpdateRequest>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
