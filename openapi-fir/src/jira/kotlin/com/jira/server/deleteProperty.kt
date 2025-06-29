package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete a property from a board
 * Removes the property from the board identified by the id. Ths user removing the property is required to have permissions to modify the board.
 */
public fun Route.deleteProperty(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/agile/1.0/board/{boardId}/properties/{propertyKey}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
