package com.jira.server

import com.jira.AttachmentMetaBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get attachment capabilities
 * Returns the meta information for an attachments, specifically if they are enabled and the maximum upload size allowed.
 */
public fun Route.getAttachmentMeta(action: suspend ApplicationCall.() -> AttachmentMetaBean) {
  route(path = """/api/2/attachment/meta""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
