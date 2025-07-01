package com.jira.server

import com.jira.DefaultShareScopeBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get default share scope
 * Returns the default share scope of the logged-in user
 */
public fun Route.getDefaultShareScope(action: suspend ApplicationCall.() -> DefaultShareScopeBean) {
  route(path = """/api/2/filter/defaultShareScope""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
