package com.jira.server

import com.jira.PriorityJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get paginated issue priorities
 * Returns a page with list of issue priorities whose names (or their translations) match query
 */
public fun Route.getPriorities_1(action: suspend ApplicationCall.() -> PriorityJsonBean) {
  route(path = """/api/2/priority/page""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
