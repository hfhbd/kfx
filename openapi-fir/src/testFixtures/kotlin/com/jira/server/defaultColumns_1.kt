package com.jira.server

import com.jira.ColumnLayout
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get default columns for filter
 * Returns the default columns for the given filter. Currently logged in user will be used as the user making such request.
 */
public fun Route.defaultColumns_1(action: suspend ApplicationCall.() -> ColumnLayout) {
  route(path = """/api/2/filter/{id}/columns""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
