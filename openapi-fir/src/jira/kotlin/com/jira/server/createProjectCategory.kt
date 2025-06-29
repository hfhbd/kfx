package com.jira.server

import com.jira.ProjectCategoryBean
import com.jira.ProjectCategoryJsonBean
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
 * Create project category
 * Create a project category.
 */
public fun Route.createProjectCategory(action: suspend ApplicationCall.(ProjectCategoryBean) -> ProjectCategoryJsonBean) {
  route(path = """/api/2/projectCategory""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<ProjectCategoryBean>()
          val response = call.action(body)
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
