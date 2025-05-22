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
 * Get a permission scheme by ID
 * Returns a permission scheme identified by the given id.
 */
public fun Route.getPermissionScheme(action: suspend ApplicationCall.() -> PermissionSchemeBean) {
  route(path = """/api/2/permissionscheme/{schemeId}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
