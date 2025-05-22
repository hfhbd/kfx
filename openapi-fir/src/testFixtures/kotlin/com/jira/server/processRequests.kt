package com.jira.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Long

/**
 * Execute pending reindex requests
 * Executes any pending reindex requests. Execution is asynchronous - progress of the returned tasks can be monitored through other REST calls.
 */
public fun Route.processRequests(action: suspend ApplicationCall.() -> Long) {
  route(path = """/api/2/reindex/request""") {
    accept(Json) {
      post {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
