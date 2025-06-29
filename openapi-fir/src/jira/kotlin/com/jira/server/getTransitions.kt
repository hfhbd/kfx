package com.jira.server

import com.jira.TransitionsMetaBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get list of transitions possible for an issue
 * Get a list of the transitions possible for this issue by the current user, along with fields that are required and their types.
 * Fields will only be returned if `expand=transitions.fields`.
 * The fields in the metadata correspond to the fields in the transition screen for that transition.
 * Fields not in the screen will not be in the metadata.
 */
public fun Route.getTransitions(action: suspend ApplicationCall.() -> TransitionsMetaBean) {
  route(path = """/api/2/issue/{issueIdOrKey}/transitions""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
