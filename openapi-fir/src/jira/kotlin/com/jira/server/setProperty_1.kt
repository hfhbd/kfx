package com.jira.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Set a property on a comment
 * Sets the value of the specified comment's property.
 */
public fun Route.setProperty_1(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/comment/{commentId}/properties/{propertyKey}""") {
    contentType(Json) {
      put {
        call.action()
        call.respond(OK)
      }
    }
  }
}
