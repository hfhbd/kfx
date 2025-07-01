package com.jira.server

import com.jira.RemoteIssueLinkCreateOrUpdateRequest
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Update remote issue link
 * Updates a remote issue link from a JSON representation. Any fields not provided are set to null.
 */
public fun Route.updateRemoteIssueLink(action: suspend ApplicationCall.(RemoteIssueLinkCreateOrUpdateRequest) -> Unit) {
  route(path = """/api/2/issue/{issueIdOrKey}/remotelink/{linkId}""") {
    contentType(Json) {
      put {
        val body = call.receive<RemoteIssueLinkCreateOrUpdateRequest>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
