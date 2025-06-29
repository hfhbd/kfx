package com.example.server

import com.example.StorageManagerResponse
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
 * Provides storage resources that can be used for synchronisation runs. It creates a blob file in Azure Storage.
 */
public fun Route.createInAzure(action: suspend ApplicationCall.() -> StorageManagerResponse) {
  route(path = """/storages/azure""") {
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
