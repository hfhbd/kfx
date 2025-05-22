package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Archive a project
 * Archives a project
 */
public fun Route.archiveProject(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/project/{projectIdOrKey}/archive""") {
    put {
      call.action()
      call.respond(NoContent)
    }
  }
}
