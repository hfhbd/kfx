package com.example.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Stops a running synchronization run
 */
public fun Route.stopSynchronizationRun(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/synchronizationRuns/{id}/stop""") {
    contentType(Json) {
      accept(Json) {
        post {
          call.action()
          call.respond(OK)
        }
      }
    }
  }
}
