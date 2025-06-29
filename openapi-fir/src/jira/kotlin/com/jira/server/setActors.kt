package com.jira.server

import com.jira.ProjectRoleActorsUpdateBean
import com.jira.ProjectRoleBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route

/**
 * Update project role with actors
 * Updates a project role to include the specified actors (users or groups). Can be also used to clear roles to not include any users or groups. For user actors, their usernames should be used.
 */
public fun Route.setActors(action: suspend ApplicationCall.(ProjectRoleActorsUpdateBean) -> ProjectRoleBean) {
  route(path = """/api/2/project/{projectIdOrKey}/role/{id}""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<ProjectRoleActorsUpdateBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
