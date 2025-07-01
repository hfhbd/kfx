package com.jira.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Boolean
import kotlin.Unit

/**
 * Pin a comment
 * Pins a comment to the top of the comment list.
 */
public fun Route.setPinComment(action: suspend ApplicationCall.(Boolean) -> Unit) {
  route(path = """/api/2/issue/{issueIdOrKey}/comment/{id}/pin""") {
    contentType(Json) {
      put {
        val body = call.receive<Boolean>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
