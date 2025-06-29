package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Request node index snapshot
 * Request current index from node (the request is processed asynchronously)
 */
public fun Route.requestCurrentIndexFromNode(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/cluster/index-snapshot/{nodeId}""") {
    put {
      call.action()
      call.respond(OK)
    }
  }
}
