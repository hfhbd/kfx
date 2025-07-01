package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Add user to application
 * Add user to given application. Admin permission will be required to perform this operation.
 */
public fun Route.addUserToApplication_1(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/user/application""") {
    post {
      call.action()
      call.respond(OK)
    }
  }
}
