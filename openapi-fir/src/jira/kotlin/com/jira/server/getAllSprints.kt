package com.jira.server

import com.jira.SprintBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all sprints from a board
 * Returns all sprints from a board, for a given board Id. This only includes sprints that the user has permission to view.
 */
public fun Route.getAllSprints(action: suspend ApplicationCall.() -> SprintBean) {
  route(path = """/agile/1.0/board/{boardId}/sprint""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
