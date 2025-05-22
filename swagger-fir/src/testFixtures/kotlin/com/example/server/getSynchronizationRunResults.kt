package com.example.server

import com.example.LeanIxDataInterchangeFormat
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
 * Returns the results of a finished synchronization run
 */
public fun Route.getSynchronizationRunResults(action: suspend ApplicationCall.() -> LeanIxDataInterchangeFormat) {
  route(path = """/synchronizationRuns/{id}/results""") {
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
