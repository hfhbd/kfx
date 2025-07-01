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
 * Find users with browse permission
 * Returns a list of active users that match the search string. This resource cannot be accessed anonymously and requires the Browse Users global permission. Given an issue key this resource will provide a list of users that match the search string and have the browse issue permission for the issue provided.
 */
public fun Route.findUsersWithBrowsePermission(action: suspend ApplicationCall.() -> UserBean) {
  route(path = """/api/2/user/viewissue/search""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
