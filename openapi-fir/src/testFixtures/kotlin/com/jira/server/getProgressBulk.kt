package com.jira.server

import com.jira.ReindexRequestBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get progress of multiple reindex requests
 * Retrieves the progress of multiple reindex requests. Only reindex requests that actually exist will be returned in the results.
 */
public fun Route.getProgressBulk(action: suspend ApplicationCall.() -> ReindexRequestBean) {
  route(path = """/api/2/reindex/request/bulk""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
