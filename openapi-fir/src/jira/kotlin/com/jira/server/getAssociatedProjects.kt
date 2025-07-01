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
 * Get all of the associated projects for specified scheme
 * For the specified issue type scheme, returns all of the associated projects
 */
public fun Route.getAssociatedProjects(action: suspend ApplicationCall.() -> ProjectBean) {
  route(path = """/api/2/issuetypescheme/{schemeId}/associations""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
