package com.jira.server

import com.jira.IndexSnapshotStatusBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get index snapshot creation status
 * Checks if index snapshot creation is currently running
 */
public fun Route.isIndexSnapshotRunning(action: suspend ApplicationCall.() -> IndexSnapshotStatusBean) {
  route(path = """/api/2/index-snapshot/isRunning""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
