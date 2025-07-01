package com.jira.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route
import kotlin.String

/**
 * Get current password policy requirements
 * Returns the list of requirements for the current password policy. For example, "The password must have at least 10 characters.", "The password must not be similar to the user's name or email address.", etc.
 */
public fun Route.getPasswordPolicy(action: suspend ApplicationCall.() -> String) {
  route(path = """/api/2/password/policy""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
