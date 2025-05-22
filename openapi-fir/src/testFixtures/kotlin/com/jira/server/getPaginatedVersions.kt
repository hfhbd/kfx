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
 * Get paginated versions
 * Retrieve paginated collection of versions matching given query optionally filtered by given project IDs.
 */
public fun Route.getPaginatedVersions(action: suspend ApplicationCall.() -> VersionBean) {
  route(path = """/api/2/version""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
