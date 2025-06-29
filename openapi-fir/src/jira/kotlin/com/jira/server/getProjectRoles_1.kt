package com.jira.server

import com.jira.ProjectRoleBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all project roles
 * Get all the ProjectRoles available in Jira. Currently this list is global.
 */
public fun Route.getProjectRoles_1(action: suspend ApplicationCall.() -> ProjectRoleBean) {
  route(path = """/api/2/role""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
