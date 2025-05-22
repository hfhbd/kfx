package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Get email templates as zip file
 * Creates a zip file containing email templates at local home and returns the file.
 */
public fun Route.downloadEmailTemplates(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/email-templates""") {
    `get` {
      call.action()
      call.respond(OK)
    }
  }
}
