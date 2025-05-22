package com.jira.server

import com.jira.CommentJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route

/**
 * Update a comment
 * Updates an existing comment using its JSON representation.
 */
public fun Route.updateComment(action: suspend ApplicationCall.(CommentJsonBean) -> CommentJsonBean) {
  route(path = """/api/2/issue/{issueIdOrKey}/comment/{id}""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<CommentJsonBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
