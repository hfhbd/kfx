package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete user
 * Removes user and its references (like project roles associations, watches, history). Note: user references will not be removed if multiple User Directories are used and there is a user with the same name existing in another directory (shadowing user).
 */
public fun Route.removeUser(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/user""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
