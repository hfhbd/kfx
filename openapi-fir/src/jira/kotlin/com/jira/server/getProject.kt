package com.jira.server

import com.jira.ProjectBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get a project by ID or key
 * Returns a full representation of a project in JSON format. All project keys associated with the project will only be returned if <code>expand=projectKeys</code>.
 */
public fun Route.getProject(action: suspend ApplicationCall.() -> ProjectBean) {
  route(path = """/api/2/project/{projectIdOrKey}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
