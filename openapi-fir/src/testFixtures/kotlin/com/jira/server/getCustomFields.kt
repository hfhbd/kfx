package com.jira.server

import com.jira.CustomFieldBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get custom fields with pagination
 * Returns a list of Custom Fields in the given range.
 */
public fun Route.getCustomFields(action: suspend ApplicationCall.() -> CustomFieldBean) {
  route(path = """/api/2/customFields""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
