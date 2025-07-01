package com.jira.server

import com.jira.PrioritySchemeBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get a priority scheme by ID
 * Gets a full representation of a priority scheme in JSON format.
 */
public fun Route.getPriorityScheme(action: suspend ApplicationCall.() -> PrioritySchemeBean) {
  route(path = """/api/2/priorityschemes/{schemeId}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
