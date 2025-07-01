package com.jira.server

import com.jira.AttachmentBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get the meta-data for an attachment, including the URI of the actual attached file
 * Returns the meta-data for an attachment, including the URI of the actual attached file.
 */
public fun Route.getAttachment(action: suspend ApplicationCall.() -> AttachmentBean) {
  route(path = """/api/2/attachment/{id}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
