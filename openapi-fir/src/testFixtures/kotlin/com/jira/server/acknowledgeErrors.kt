package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Retry cluster upgrade
 * Retries the cluster upgrade.
 */
public fun Route.acknowledgeErrors(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/cluster/zdu/retryUpgrade""") {
    post {
      call.action()
      call.respond(OK)
    }
  }
}
