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
 * Get default system columns for issue navigator
 * Returns the default system columns for issue navigator. Admin permission will be required.
 */
public fun Route.getIssueNavigatorDefaultColumns(action: suspend ApplicationCall.() -> ColumnOptions) {
  route(path = """/api/2/settings/columns""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
