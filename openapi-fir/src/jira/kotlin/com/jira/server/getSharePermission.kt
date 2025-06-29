package com.jira.server

import com.jira.FilterPermissionBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get a single share permission of filter
 * Returns a single share permission of the given filter
 */
public fun Route.getSharePermission(action: suspend ApplicationCall.() -> FilterPermissionBean) {
  route(path = """/api/2/filter/{id}/permission/{permissionId}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
