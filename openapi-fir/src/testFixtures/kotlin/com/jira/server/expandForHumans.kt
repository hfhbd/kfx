package com.jira.server

import com.jira.HumanReadableArchive
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get human-readable attachment expansion
 * Tries to expand an attachment. Output is human-readable and subject to change.
 */
public fun Route.expandForHumans(action: suspend ApplicationCall.() -> HumanReadableArchive) {
  route(path = """/api/2/attachment/{id}/expand/human""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
