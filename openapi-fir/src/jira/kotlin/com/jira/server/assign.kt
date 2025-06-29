package com.jira.server

import com.jira.UserBean
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
 * Assign an issue to a user
 * Assign an issue to a user.
 */
public fun Route.assign(action: suspend ApplicationCall.(UserBean) -> Unit) {
  route(path = """/api/2/issue/{issueIdOrKey}/assignee""") {
    contentType(Json) {
      put {
        val body = call.receive<UserBean>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
