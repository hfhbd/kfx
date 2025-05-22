package com.jira.server

import com.jira.VersionBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get project versions
 * Contains a full representation of a the specified project's versions.
 */
public fun Route.getProjectVersions(action: suspend ApplicationCall.() -> VersionBean) {
  route(path = """/api/2/project/{projectIdOrKey}/versions""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
