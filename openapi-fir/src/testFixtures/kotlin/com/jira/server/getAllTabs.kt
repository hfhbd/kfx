package com.jira.server

import com.jira.ScreenableTabBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all tabs for a screen
 * Returns a list of all tabs for the given screen.
 */
public fun Route.getAllTabs(action: suspend ApplicationCall.() -> ScreenableTabBean) {
  route(path = """/api/2/screens/{screenId}/tabs""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
