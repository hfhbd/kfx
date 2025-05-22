package com.jira.server

import com.jira.PermissionSchemeAttributeBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get scheme attribute by key
 * Returns the attribute for a permission scheme specified by permission scheme id and attribute key.
 */
public fun Route.getSchemeAttribute(action: suspend ApplicationCall.() -> PermissionSchemeAttributeBean) {
  route(path = """/api/2/permissionscheme/{permissionSchemeId}/attribute/{attributeKey}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
