package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete the board
 * Deletes the board.
 */
public fun Route.deleteBoard(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/agile/1.0/board/{boardId}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
