package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Start cluster upgrade
 * Starts the cluster upgrade.
 */
public fun Route.setReadyToUpgrade(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/cluster/zdu/start""") {
    post {
      call.action()
      call.respond(Created)
    }
  }
}
