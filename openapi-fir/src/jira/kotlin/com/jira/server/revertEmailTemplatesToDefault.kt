package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Update email templates to default
 * Replaces the current email templates pack with default templates, which are copied over from Jira binaries.
 */
public fun Route.revertEmailTemplatesToDefault(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/email-templates/revert""") {
    post {
      call.action()
      call.respond(OK)
    }
  }
}
