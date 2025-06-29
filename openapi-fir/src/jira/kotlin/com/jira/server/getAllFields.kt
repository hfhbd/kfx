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
 * Get all fields for a tab
 * Gets all fields for a given tab.
 */
public fun Route.getAllFields(action: suspend ApplicationCall.() -> ScreenableFieldBean) {
  route(path = """/api/2/screens/{screenId}/tabs/{tabId}/fields""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
