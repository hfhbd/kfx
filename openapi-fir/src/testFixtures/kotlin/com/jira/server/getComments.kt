package com.jira.server

import com.jira.CommentsWithPaginationJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get comments for an issue
 * Returns all comments for an issue. Results can be ordered by the 'created' field which means the date a comment was added.
 */
public fun Route.getComments(action: suspend ApplicationCall.() -> CommentsWithPaginationJsonBean) {
  route(path = """/api/2/issue/{issueIdOrKey}/comment""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
