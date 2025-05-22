package com.jira.server

import com.jira.CreateUpdateRoleRequestBean
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
 * Create a new project role
 * Creates a new ProjectRole to be available in Jira. The created role does not have any default actors assigned.
 */
public fun Route.createProjectRole(action: suspend ApplicationCall.(CreateUpdateRoleRequestBean) -> ProjectRoleBean) {
  route(path = """/api/2/role""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<CreateUpdateRoleRequestBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
