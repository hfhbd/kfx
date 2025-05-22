package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Start exposing JMX metrics
 * Starts exposing JMX metrics
 */
public fun Route.start(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/monitoring/jmx/startExposing""") {
    post {
      call.action()
      call.respond(OK)
    }
  }
}
