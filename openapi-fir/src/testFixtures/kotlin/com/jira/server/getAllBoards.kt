package com.jira.server

import com.jira.BoardBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all boards
 * Returns all boards. This only includes boards that the user has permission to view.
 */
public fun Route.getAllBoards(action: suspend ApplicationCall.() -> BoardBean) {
  route(path = """/agile/1.0/board""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
