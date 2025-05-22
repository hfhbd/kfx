package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Reset default columns to system default
 * Reset the default columns for the given user to the system default. Admin permission will be required to get columns for a user other than the currently logged in user.
 */
public fun Route.resetColumns(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/user/columns""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
