package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.Accepted
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Restore an archived project
 * Restores an archived project. In case of success restored project should be re-indexed.
 */
public fun Route.restoreProject(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/project/{projectIdOrKey}/restore""") {
    put {
      call.action()
      call.respond(Accepted)
    }
  }
}
