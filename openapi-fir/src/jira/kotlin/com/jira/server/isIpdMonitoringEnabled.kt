package com.jira.server

import com.jira.IpdMonitoringRestEntity
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get if IPD Monitoring is enabled
 * Checks if IPD Monitoring is enabled
 */
public fun Route.isIpdMonitoringEnabled(action: suspend ApplicationCall.() -> IpdMonitoringRestEntity) {
  route(path = """/api/2/monitoring/ipd""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
