package com.jira.server

import com.jira.EpicBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get an epic by id or key
 * Returns the epic for a given epic Id. This epic will only be returned if the user has permission to view it.
 */
public fun Route.getEpic(action: suspend ApplicationCall.() -> EpicBean) {
  route(path = """/agile/1.0/epic/{epicIdOrKey}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
