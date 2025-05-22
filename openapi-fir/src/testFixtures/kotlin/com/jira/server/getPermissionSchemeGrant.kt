package com.jira.server

import com.jira.PermissionGrantBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get a permission grant by ID
 * Returns a permission grant identified by the given id.
 */
public fun Route.getPermissionSchemeGrant(action: suspend ApplicationCall.() -> PermissionGrantBean) {
  route(path = """/api/2/permissionscheme/{schemeId}/permission/{permissionId}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
