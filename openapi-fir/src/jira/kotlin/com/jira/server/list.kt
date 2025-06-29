package com.jira.server

import com.jira.DashboardsBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all dashboards with optional filtering
 * Returns a list of all dashboards, optionally filtering them.
 */
public fun Route.list(action: suspend ApplicationCall.() -> DashboardsBean) {
  route(path = """/api/2/dashboard""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
