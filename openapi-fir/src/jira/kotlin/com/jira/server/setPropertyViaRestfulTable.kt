package com.jira.server

import com.jira.Property
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.put
import io.ktor.server.routing.route

/**
 * Update an application property
 * Update an application property via PUT. The "value" field present in the PUT will override the existing value.
 */
public fun Route.setPropertyViaRestfulTable(action: suspend ApplicationCall.() -> Property) {
  route(path = """/api/2/application-properties/{id}""") {
    accept(Json) {
      put {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
