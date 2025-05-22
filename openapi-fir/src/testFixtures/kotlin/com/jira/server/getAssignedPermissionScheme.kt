package com.jira.server

import com.jira.PermissionSchemeBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get assigned permission scheme
 * Gets a permission scheme assigned with a project
 */
public fun Route.getAssignedPermissionScheme(action: suspend ApplicationCall.() -> PermissionSchemeBean) {
  route(path = """/api/2/project/{projectKeyOrId}/permissionscheme""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
