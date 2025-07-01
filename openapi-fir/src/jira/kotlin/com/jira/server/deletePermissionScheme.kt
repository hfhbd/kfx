package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete a permission scheme by ID
 * Deletes a permission scheme identified by the given id.
 */
public fun Route.deletePermissionScheme(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/permissionscheme/{schemeId}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
