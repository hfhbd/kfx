package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Update node state to offline
 * Change the node's state to offline if the node is reporting as active, but is not alive.
 */
public fun Route.changeNodeStateToOffline(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/cluster/node/{nodeId}/offline""") {
    put {
      call.action()
      call.respond(OK)
    }
  }
}
