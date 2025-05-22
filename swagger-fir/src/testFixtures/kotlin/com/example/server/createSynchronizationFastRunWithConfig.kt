package com.example.server

import com.example.FastRunResponse
import com.example.InputWithProcessorConfig
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Starts a new fast run synchronization using the processor configuration and input object provided in the request.
 * >__Please do not use this endpoint for production use cases. It was built for testing configurations only.__
 */
public fun Route.createSynchronizationFastRunWithConfig(action: suspend ApplicationCall.(InputWithProcessorConfig) -> FastRunResponse) {
  route(path = """/fastSynchronizationRuns/withConfig""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<InputWithProcessorConfig>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
