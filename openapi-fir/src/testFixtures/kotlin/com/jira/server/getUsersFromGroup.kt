package com.jira.server

import com.jira.UserJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get users from a specified group
 * Returns a paginated list of users who are members of the specified group and its subgroups
 */
public fun Route.getUsersFromGroup(action: suspend ApplicationCall.() -> UserJsonBean) {
  route(path = """/api/2/group/member""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
