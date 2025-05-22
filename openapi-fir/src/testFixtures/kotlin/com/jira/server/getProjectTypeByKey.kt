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
 * Get project type by key
 * Returns the project type with the given key. In case of anonymous checks if they can access at least one project.
 */
public fun Route.getProjectTypeByKey(action: suspend ApplicationCall.() -> ProjectTypeBean) {
  route(path = """/api/2/project/type/{projectTypeKey}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
