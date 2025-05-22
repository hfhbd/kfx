package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete a permission grant from a scheme
 * Deletes a permission grant from a permission scheme.
 */
public fun Route.deletePermissionSchemeEntity(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/permissionscheme/{schemeId}/permission/{permissionId}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
