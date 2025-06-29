package com.jira.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route
import kotlin.Boolean

/**
 * Check if JMX metrics are being exposed
 * Checks if JMX metrics are being exposed
 */
public fun Route.areMetricsExposed(action: suspend ApplicationCall.() -> Boolean) {
  route(path = """/api/2/monitoring/jmx/areMetricsExposed""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
