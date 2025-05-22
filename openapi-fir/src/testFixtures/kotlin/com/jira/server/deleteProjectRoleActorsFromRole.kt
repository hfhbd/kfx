package com.jira.server

import com.jira.ProjectRoleActorsBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.delete
import io.ktor.server.routing.route

/**
 * Removes default actor from a role
 * Removes default actor from the given role.
 */
public fun Route.deleteProjectRoleActorsFromRole(action: suspend ApplicationCall.() -> ProjectRoleActorsBean) {
  route(path = """/api/2/role/{id}/actors""") {
    accept(Json) {
      delete {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
