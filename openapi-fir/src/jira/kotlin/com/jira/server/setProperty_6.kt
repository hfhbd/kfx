package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Update a sprint's property
 * Sets the value of the specified sprint's property. You can use this resource to store a custom data against the sprint identified by the id. The user who stores the data is required to have permissions to modify the sprint.
 */
public fun Route.setProperty_6(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/agile/1.0/sprint/{sprintId}/properties/{propertyKey}""") {
    put {
      call.action()
      call.respond(OK)
    }
  }
}
