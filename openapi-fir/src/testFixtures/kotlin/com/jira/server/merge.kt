package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Merge versions
 * Merge versions
 */
public fun Route.merge(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/version/{id}/mergeto/{moveIssuesTo}""") {
    put {
      call.action()
      call.respond(NoContent)
    }
  }
}
