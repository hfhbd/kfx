package com.jira.server

import com.jira.ResolutionBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get paginated filtered resolutions
 * Returns paginated list of filtered resolutions.
 */
public fun Route.getPaginatedResolutions(action: suspend ApplicationCall.() -> ResolutionBean) {
  route(path = """/api/2/resolution/page""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
