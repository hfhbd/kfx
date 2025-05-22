package com.jira.server

import com.jira.ApplicationRoleBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get application role by key
 * Returns the ApplicationRole with passed key if it exists.
 */
public fun Route.get_4(action: suspend ApplicationCall.() -> ApplicationRoleBean) {
  route(path = """/api/2/applicationrole/{key}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
