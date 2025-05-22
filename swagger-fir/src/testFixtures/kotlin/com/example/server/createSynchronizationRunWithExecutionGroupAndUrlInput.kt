package com.example.server

import com.example.DataProvider
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
 * Starts a new synchronization run using a DataProvider information to obtain the LDIF input, but choose a configuration based on execution group.
 */
public fun Route.createSynchronizationRunWithExecutionGroupAndUrlInput(action: suspend ApplicationCall.(DataProvider) -> SynchronizationRunWithConfiguration) {
  route(path = """/synchronizationRuns/withExecutionGroupAndUrlInput""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<DataProvider>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
