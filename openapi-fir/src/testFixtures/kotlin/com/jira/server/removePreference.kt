package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete user preference
 * Removes preference of the currently logged in user. Preference key must be provided as input parameters (key). If key parameter is not provided or wrong - status code 404. If preference is unset - status code 204.
 */
public fun Route.removePreference(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/mypreferences""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
