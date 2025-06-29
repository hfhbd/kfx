package com.jira.server

import com.jira.PrioritySchemeListBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all priority schemes
 * Returns all priority schemes. All project keys associated with the priority scheme will only be returned if additional query parameter is provided <code>expand=schemes.projectKeys</code>
 */
public fun Route.getPrioritySchemes(action: suspend ApplicationCall.() -> PrioritySchemeListBean) {
  route(path = """/api/2/priorityschemes""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
