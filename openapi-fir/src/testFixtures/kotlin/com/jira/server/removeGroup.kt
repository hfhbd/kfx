package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete a specified group
 * Deletes a group by given group parameter
 */
public fun Route.removeGroup(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/group""") {
    delete {
      call.action()
      call.respond(OK)
    }
  }
}
