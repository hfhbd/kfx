package com.jira.server

import com.jira.ProjectIdentity
import com.jira.ProjectInputBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Create a new project
 * Creates a new project
 */
public fun Route.createProject(action: suspend ApplicationCall.(ProjectInputBean) -> ProjectIdentity) {
  route(path = """/api/2/project""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<ProjectInputBean>()
          val response = call.action(body)
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
