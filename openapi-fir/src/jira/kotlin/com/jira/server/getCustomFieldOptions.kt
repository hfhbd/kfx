package com.jira.server

import com.jira.CustomFieldOptionsBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get custom field options
 * Returns custom field's options defined in a given context composed of projects and issue types.
 */
public fun Route.getCustomFieldOptions(action: suspend ApplicationCall.() -> CustomFieldOptionsBean) {
  route(path = """/api/2/customFields/{customFieldId}/options""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
