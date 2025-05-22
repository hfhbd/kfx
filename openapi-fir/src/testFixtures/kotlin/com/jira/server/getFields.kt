package com.jira.server

import com.jira.FieldBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all fields, both System and Custom
 * Returns a list of all fields, both System and Custom
 */
public fun Route.getFields(action: suspend ApplicationCall.() -> FieldBean) {
  route(path = """/api/2/field""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
