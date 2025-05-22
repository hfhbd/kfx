package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Unmap all sprints from being synced
 * Sets the Synced flag to false for all sprints on this Jira instance. This operation is intended for cleanup only. It is highly destructive and not reversible. Use with caution.
 */
public fun Route.unmapAllSprints(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/agile/1.0/sprint/unmap-all""") {
    put {
      call.action()
      call.respond(NoContent)
    }
  }
}
