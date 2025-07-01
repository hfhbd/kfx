package com.jira.server

import com.jira.CommentJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get a comment by id
 * Returns a single comment.
 */
public fun Route.getComment(action: suspend ApplicationCall.() -> CommentJsonBean) {
  route(path = """/api/2/issue/{issueIdOrKey}/comment/{id}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
