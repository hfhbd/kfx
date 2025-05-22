package com.jira.server

import io.ktor.http.ContentType.Application.FormUrlEncoded
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Set default columns for user
 * Sets the default columns for the given user. Admin permission will be required to get columns for a user other than the currently logged in user.
 */
public fun Route.setColumnsUrlEncoded(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/user/columns""") {
    contentType(FormUrlEncoded) {
      put {
        call.action()
        call.respond(OK)
      }
    }
  }
}
