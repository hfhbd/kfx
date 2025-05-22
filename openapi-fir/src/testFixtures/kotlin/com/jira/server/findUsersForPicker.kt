package com.jira.server

import com.jira.UserPickerResultsBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Find users for picker by query
 * Returns a list of users matching query with highlighting.
 */
public fun Route.findUsersForPicker(action: suspend ApplicationCall.() -> UserPickerResultsBean) {
  route(path = """/api/2/user/picker""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
