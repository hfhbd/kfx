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
 * Find assignable users by username
 * Returns a list of users that match the search string. This resource cannot be accessed anonymously. Please note that this resource should be called with an issue key when a list of assignable users is retrieved. For create only a project key should be supplied. The list of assignable users may be incorrect if it's called with the project key for editing.
 */
public fun Route.findAssignableUsers_1(action: suspend ApplicationCall.() -> UserBean) {
  route(path = """/api/2/user/assignable/search""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
