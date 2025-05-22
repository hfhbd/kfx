package com.jira.server

import com.jira.AttachmentArchiveImpl
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get raw attachment expansion
 * Tries to expand an attachment. Output is raw and should be backwards-compatible through the course of time.
 */
public fun Route.expandForMachines(action: suspend ApplicationCall.() -> AttachmentArchiveImpl) {
  route(path = """/api/2/attachment/{id}/expand/raw""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
