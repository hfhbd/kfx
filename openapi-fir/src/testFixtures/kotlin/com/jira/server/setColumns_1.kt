package com.jira.server

import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Set default columns for filter
 * Sets the default columns for the given filter
 */
public fun Route.setColumns_1(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/filter/{id}/columns""") {
    contentType(ContentType.parse("*/*")) {
      put {
        call.action()
        call.respond(OK)
      }
    }
  }
}
