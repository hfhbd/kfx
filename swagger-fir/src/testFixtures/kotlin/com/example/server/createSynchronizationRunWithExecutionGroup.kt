package com.example.server

import com.example.Input
import com.example.SynchronizationRunWithConfiguration
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
 * Starts a new synchronization run using combined processor configuration within an execution group and input object provided in the request.
 */
public fun Route.createSynchronizationRunWithExecutionGroup(action: suspend ApplicationCall.(Input) -> SynchronizationRunWithConfiguration) {
  route(path = """/synchronizationRuns/withExecutionGroup""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<Input>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
