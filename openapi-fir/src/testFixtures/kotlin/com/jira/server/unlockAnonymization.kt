package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete stale user anonymization task
 * Removes stale user anonymization task, for scenarios when the node that was executing it is no longer alive. Use it only after making sure that the parent node of the task is actually down, and not just having connectivity issues.
 */
public fun Route.unlockAnonymization(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/user/anonymization/unlock""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
