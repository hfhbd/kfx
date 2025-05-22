package com.jira.server

import com.jira.ActorsMap
import com.jira.ProjectRoleBean
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
 * Add actor to project role
 * Adds an actor (user or group) to a project role. For user actors, their usernames should be used.
 */
public fun Route.addActorUsers(action: suspend ApplicationCall.(ActorsMap) -> ProjectRoleBean) {
  route(path = """/api/2/project/{projectIdOrKey}/role/{id}""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<ActorsMap>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
