package com.jira.server

import com.jira.CurrentUser
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get current user session information
 * Returns information about the currently authenticated user's session. If the caller is not authenticated they will get a 401 Unauthorized status code.
 */
public fun Route.currentUser(action: suspend ApplicationCall.() -> CurrentUser) {
  route(path = """/auth/1/session""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
