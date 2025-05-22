package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete watcher from issue
 * Removes a user from an issue's watcher list.
 */
public fun Route.removeWatcher_1(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/issue/{issueIdOrKey}/watchers""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
