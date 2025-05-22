package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete current user session
 * Logs the current user out of Jira, destroying the existing session, if any.
 */
public fun Route.logout(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/auth/1/session""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
