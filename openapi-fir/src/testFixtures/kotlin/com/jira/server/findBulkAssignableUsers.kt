package com.jira.server

import com.jira.UserBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Find bulk assignable users
 * Returns a list of users that match the search string and can be assigned issues for all the given projects.
 */
public fun Route.findBulkAssignableUsers(action: suspend ApplicationCall.() -> UserBean) {
  route(path = """/api/2/user/assignable/multiProjectSearch""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
