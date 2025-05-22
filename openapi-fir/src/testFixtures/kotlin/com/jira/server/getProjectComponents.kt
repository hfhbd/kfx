package com.jira.server

import com.jira.ComponentBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get project components
 * Contains a full representation of the specified project's components.
 */
public fun Route.getProjectComponents(action: suspend ApplicationCall.() -> ComponentBean) {
  route(path = """/api/2/project/{projectIdOrKey}/components""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
