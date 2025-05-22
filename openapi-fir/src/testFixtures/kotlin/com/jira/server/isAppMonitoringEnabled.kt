package com.jira.server

import com.jira.AppMonitoringRestEntity
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get App Monitoring status
 * Checks if App Monitoring is enabled
 */
public fun Route.isAppMonitoringEnabled(action: suspend ApplicationCall.() -> AppMonitoringRestEntity) {
  route(path = """/api/2/monitoring/app""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
