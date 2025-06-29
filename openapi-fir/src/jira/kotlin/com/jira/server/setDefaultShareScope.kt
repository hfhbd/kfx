package com.jira.server

import com.jira.DefaultShareScopeBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route

/**
 * Set default share scope
 * Sets the default share scope of the logged-in user. Available values are: AUTHENTICATED (for sharing with all logged-in users) and PRIVATE (for no shares).
 */
public fun Route.setDefaultShareScope(action: suspend ApplicationCall.(DefaultShareScopeBean) -> DefaultShareScopeBean) {
  route(path = """/api/2/filter/defaultShareScope""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<DefaultShareScopeBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
