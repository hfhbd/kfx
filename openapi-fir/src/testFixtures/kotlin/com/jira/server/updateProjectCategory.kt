package com.jira.server

import com.jira.ProjectCategoryBean
import com.jira.ProjectCategoryJsonBean
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
 * Update project category
 * Modify a project category.
 */
public fun Route.updateProjectCategory(action: suspend ApplicationCall.(ProjectCategoryBean) -> ProjectCategoryJsonBean) {
  route(path = """/api/2/projectCategory/{id}""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<ProjectCategoryBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
