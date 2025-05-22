package com.jira.server

import com.jira.Property
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all advanced settings properties
 * Returns the properties that are displayed on the "General Configuration > Advanced Settings" page.
 */
public fun Route.getAdvancedSettings(action: suspend ApplicationCall.() -> Property) {
  route(path = """/api/2/application-properties/advanced-settings""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
