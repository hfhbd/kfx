package com.jira.server

import com.jira.ActorInputBean
import com.jira.ProjectRoleActorsBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Adds default actors to a role
 * Adds default actors to the given role. The request data should contain a list of usernames or a list of groups to add.
 */
public fun Route.addProjectRoleActorsToRole(action: suspend ApplicationCall.(ActorInputBean) -> ProjectRoleActorsBean) {
  route(path = """/api/2/role/{id}/actors""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<ActorInputBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
