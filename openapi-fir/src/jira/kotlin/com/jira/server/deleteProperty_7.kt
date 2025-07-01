package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete a sprint's property
 * Removes the property from the sprint identified by the id. Ths user removing the property is required to have permissions to modify the sprint.
 */
public fun Route.deleteProperty_7(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/agile/1.0/sprint/{sprintId}/properties/{propertyKey}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
