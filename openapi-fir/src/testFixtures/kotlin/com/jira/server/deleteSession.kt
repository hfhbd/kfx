package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete user session
 * Invalidates session of given user.
 */
public fun Route.deleteSession(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/user/session/{username}""") {
    delete {
      call.action()
      call.respond(OK)
    }
  }
}
