package com.jira.server

import com.jira.IndexSnapshotBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get list of available index snapshots
 * Lists available index snapshots absolute paths with timestamps
 */
public fun Route.listIndexSnapshot(action: suspend ApplicationCall.() -> IndexSnapshotBean) {
  route(path = """/api/2/index-snapshot""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
