package com.jira.server

import com.jira.ProjectBean
import com.jira.ProjectUpdateBean
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
 * Update a project
 * Updates a project. Only non null values sent in JSON will be updated in the project. Values available for the assigneeType field are: "PROJECT_LEAD" and "UNASSIGNED".
 */
public fun Route.updateProject(action: suspend ApplicationCall.(ProjectUpdateBean) -> ProjectBean) {
  route(path = """/api/2/project/{projectIdOrKey}""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<ProjectUpdateBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
