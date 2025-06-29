package com.jira.server

import com.jira.PinnedCommentJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get pinned comments for an issue
 * Returns all pinned to the issue comments.
 */
public fun Route.getPinnedComments(action: suspend ApplicationCall.() -> PinnedCommentJsonBean) {
  route(path = """/api/2/issue/{issueIdOrKey}/pinned-comments""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
