package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Remove a user from a specified group
 * Removes given user from a group
 */
public fun Route.removeUserFromGroup(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/group/user""") {
    delete {
      call.action()
      call.respond(OK)
    }
  }
}
