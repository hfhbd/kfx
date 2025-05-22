package com.jira.server

import com.jira.PermissionsJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all permissions present in Jira instance
 * Returns all permissions that are present in the Jira instance - Global, Project and the global ones added by plugins
 */
public fun Route.getAllPermissions(action: suspend ApplicationCall.() -> PermissionsJsonBean) {
  route(path = """/api/2/permissions""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
