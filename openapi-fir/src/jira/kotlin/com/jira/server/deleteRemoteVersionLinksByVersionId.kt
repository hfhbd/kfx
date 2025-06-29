package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete all remote version links for version
 * Delete all remote version links for a given version ID.
 */
public fun Route.deleteRemoteVersionLinksByVersionId(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/version/{versionId}/remotelink""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
