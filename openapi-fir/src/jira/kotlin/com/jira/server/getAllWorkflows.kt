package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Get all workflows
 * Returns all workflows. The âlastModifiedDateâ is returned in Jira Complete Date/Time Format (dd/MMM/yy h:mm by default), but can also be returned as a relative date.
 */
public fun Route.getAllWorkflows(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/workflow""") {
    `get` {
      call.action()
      call.respond(OK)
    }
  }
}
