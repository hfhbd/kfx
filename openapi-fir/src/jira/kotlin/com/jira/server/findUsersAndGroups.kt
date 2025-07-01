package com.jira.server

import com.jira.UsersAndGroupsBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get users and groups matching query with highlighting
 * Returns a list of users and groups matching query with highlighting
 */
public fun Route.findUsersAndGroups(action: suspend ApplicationCall.() -> UsersAndGroupsBean) {
  route(path = """/api/2/groupuserpicker""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
