package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Run pending upgrade tasks
 * Runs any pending delayed upgrade tasks. Need Admin permissions to do this.
 */
public fun Route.runUpgradesNow(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/upgrade""") {
    post {
      call.action()
      call.respond(OK)
    }
  }
}
