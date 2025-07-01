package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Remove user from application
 * Remove user from given application. Admin permission will be required to perform this operation.
 */
public fun Route.removeUserFromApplication_1(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/user/application""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
