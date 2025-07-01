package com.jira.server

import com.jira.CustomFieldOptionBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get custom field option by ID
 * Returns a full representation of the Custom Field Option that has the given id.
 */
public fun Route.getCustomFieldOption(action: suspend ApplicationCall.() -> CustomFieldOptionBean) {
  route(path = """/api/2/customFieldOption/{id}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
