package com.example.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete a single processor configuration
 */
public fun Route.deleteProcessorConfiguration(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/configurations""") {
    contentType(Json) {
      accept(Json) {
        delete {
          call.action()
          call.respond(OK)
        }
      }
    }
  }
}
