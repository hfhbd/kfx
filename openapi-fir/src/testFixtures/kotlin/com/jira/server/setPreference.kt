package com.jira.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.String
import kotlin.Unit

/**
 * Update user preference
 * Sets preference of the currently logged in user. Preference key must be provided as input parameters (key). Value must be provided as post body. If key or value parameter is not provided - status code 404. If preference is set - status code 204.
 */
public fun Route.setPreference(action: suspend ApplicationCall.(String) -> Unit) {
  route(path = """/api/2/mypreferences""") {
    contentType(Json) {
      put {
        val body = call.receive<String>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
