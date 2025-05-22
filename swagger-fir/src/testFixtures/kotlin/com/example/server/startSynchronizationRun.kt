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
 * Starts an existing but not yet started synchronization run
 */
public fun Route.startSynchronizationRun(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/synchronizationRuns/{id}/start""") {
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
