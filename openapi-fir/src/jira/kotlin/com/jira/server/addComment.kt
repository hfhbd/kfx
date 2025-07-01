package com.jira.server

import com.jira.CommentJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Add a comment
 * Adds a new comment to an issue.
 */
public fun Route.addComment(action: suspend ApplicationCall.(CommentJsonBean) -> CommentJsonBean) {
  route(path = """/api/2/issue/{issueIdOrKey}/comment""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<CommentJsonBean>()
          val response = call.action(body)
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
