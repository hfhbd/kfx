package com.example.server

import com.example.ProcessorConfiguration
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Inserts a new processor configuration or updates an existing one
 */
public fun Route.upsertProcessorConfiguration(action: suspend ApplicationCall.(ProcessorConfiguration) -> Unit) {
  route(path = """/configurations""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<ProcessorConfiguration>()
          call.action(body)
          call.respond(NoContent)
        }
      }
    }
  }
}
