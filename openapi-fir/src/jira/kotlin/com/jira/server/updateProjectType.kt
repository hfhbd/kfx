package com.jira.server

import com.jira.ProjectBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.put
import io.ktor.server.routing.route

/**
 * Update project type
 * Updates the type of a project
 */
public fun Route.updateProjectType(action: suspend ApplicationCall.() -> ProjectBean) {
  route(path = """/api/2/project/{projectIdOrKey}/type/{newProjectTypeKey}""") {
    accept(Json) {
      put {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
