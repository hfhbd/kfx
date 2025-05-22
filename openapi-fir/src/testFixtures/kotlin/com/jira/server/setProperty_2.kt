package com.jira.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.String
import kotlin.Unit

/**
 * Update the value of a specific issue's property
 * Sets the value of the specified issue's property.
 */
public fun Route.setProperty_2(action: suspend ApplicationCall.(String) -> Unit) {
  route(path = """/api/2/issue/{issueIdOrKey}/properties/{propertyKey}""") {
    contentType(Json) {
      put {
        val body = call.receive<String>()
        call.action(body)
        call.respond(OK)
      }
    }
  }
}
