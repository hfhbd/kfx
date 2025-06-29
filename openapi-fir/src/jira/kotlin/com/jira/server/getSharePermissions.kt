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
 * Get all share permissions of filter
 * Returns all share permissions of the given filter
 */
public fun Route.getSharePermissions(action: suspend ApplicationCall.() -> FilterPermissionBean) {
  route(path = """/api/2/filter/{id}/permission""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
