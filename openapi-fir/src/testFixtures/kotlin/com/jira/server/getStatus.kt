package com.jira.server

import com.jira.StatusJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get status by ID or name
 * Returns a full representation of the Status having the given id or name.
 */
public fun Route.getStatus(action: suspend ApplicationCall.() -> StatusJsonBean) {
  route(path = """/api/2/status/{idOrName}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
