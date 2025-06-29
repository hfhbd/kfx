package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Get email types for templates
 * Returns a list of root templates mapped with Event Types. The list can be used to decide which test emails to send.
 */
public fun Route.getEmailTypes(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/email-templates/types""") {
    `get` {
      call.action()
      call.respond(OK)
    }
  }
}
