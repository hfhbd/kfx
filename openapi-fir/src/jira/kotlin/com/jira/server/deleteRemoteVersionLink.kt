package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete specific remote version link
 * Delete a specific remote version link with the given version ID and global ID.
 */
public fun Route.deleteRemoteVersionLink(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/version/{versionId}/remotelink/{globalId}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
