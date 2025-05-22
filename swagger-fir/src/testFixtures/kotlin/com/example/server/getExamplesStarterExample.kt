package com.example.server

import com.example.InputWithProcessorConfig
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Returns a starter example including an Input object and processor configuration
 */
public fun Route.getExamplesStarterExample(action: suspend ApplicationCall.() -> InputWithProcessorConfig) {
  route(path = """/examples/starterExample""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
