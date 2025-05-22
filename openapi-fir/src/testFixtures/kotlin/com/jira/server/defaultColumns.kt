package com.jira.server

import com.jira.ColumnOptions
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get default columns for user
 * Returns the default columns for the given user. Admin permission will be required to get columns for a user other than the currently logged in user.
 */
public fun Route.defaultColumns(action: suspend ApplicationCall.() -> ColumnOptions) {
  route(path = """/api/2/user/columns""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
