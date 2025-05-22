package com.jira.server

import com.jira.PermissionGrantsBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all permission grants of a scheme
 * Returns all permission grants of the given permission scheme.
 */
public fun Route.getPermissionSchemeGrants(action: suspend ApplicationCall.() -> PermissionGrantsBean) {
  route(path = """/api/2/permissionscheme/{schemeId}/permission""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
