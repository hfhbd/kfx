package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Cancel cluster upgrade
 * Cancels the ongoing cluster upgrade.
 */
public fun Route.cancelUpgrade(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/cluster/zdu/cancel""") {
    post {
      call.action()
      call.respond(Created)
    }
  }
}
