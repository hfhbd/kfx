package com.jira.server

import com.jira.ProjectTypeBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all project types
 * Returns all the project types defined on the Jira instance, not taking into account whether the license to use those project types is valid or not. In case of anonymous checks if they can access at least one project.
 */
public fun Route.getAllProjectTypes(action: suspend ApplicationCall.() -> ProjectTypeBean) {
  route(path = """/api/2/project/type""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
