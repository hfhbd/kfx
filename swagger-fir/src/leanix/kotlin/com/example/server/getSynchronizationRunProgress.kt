package com.example.server

import com.example.SyncRunInboundProgressReport
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.route

/**
 * Shows the progress of a synchronization run, it gives updated counters of the run level that is in execution.
 */
public fun Route.getSynchronizationRunProgress(action: suspend ApplicationCall.() -> SyncRunInboundProgressReport) {
  route(path = """/synchronizationRuns/{id}/progress""") {
    contentType(Json) {
      accept(Json) {
        `get` {
          val response = call.action()
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
