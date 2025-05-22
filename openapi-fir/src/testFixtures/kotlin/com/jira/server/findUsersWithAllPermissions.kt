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
 * Find users with all specified permissions
 * Returns a list of active users that match the search string and have all specified permissions for the project or issue. This resource can be accessed by users with ADMINISTER_PROJECT permission for the project or global ADMIN or SYSADMIN rights. This endpoint can cause serious performance issues and will be removed in Jira 9.0.
 */
public fun Route.findUsersWithAllPermissions(action: suspend ApplicationCall.() -> UserBean) {
  route(path = """/api/2/user/permission/search""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
