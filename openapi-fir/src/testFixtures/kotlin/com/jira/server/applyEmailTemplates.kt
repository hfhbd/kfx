package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Update email templates with previously uploaded pack
 * Replaces the current email templates pack with previously uploaded one, if exists.
 */
public fun Route.applyEmailTemplates(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/email-templates/apply""") {
    post {
      call.action()
      call.respond(OK)
    }
  }
}
