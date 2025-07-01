package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete a cluster node
 * Delete the node from the cluster if state of node is OFFLINE.
 */
public fun Route.deleteNode(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/cluster/node/{nodeId}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
