package com.jira.server

import io.ktor.http.ContentType.Application.Zip
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Update email templates with zip file
 * Extracts given zip file to temporary templates folder. If the folder already exists it will replace it's content
 */
public fun Route.uploadEmailTemplates(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/email-templates""") {
    contentType(Zip) {
      post {
        call.action()
        call.respond(OK)
      }
    }
  }
}
