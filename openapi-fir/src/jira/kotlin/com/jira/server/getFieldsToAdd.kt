package com.jira.server

import com.jira.ScreenableFieldBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get available fields for screen
 * Gets available fields for screen. i.e ones that haven't already been added.
 */
public fun Route.getFieldsToAdd(action: suspend ApplicationCall.() -> ScreenableFieldBean) {
  route(path = """/api/2/screens/{screenId}/availableFields""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
