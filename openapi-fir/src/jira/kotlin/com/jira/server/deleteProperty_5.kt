package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete property from project
 * Removes the property from the project identified by the key or by the id.
 */
public fun Route.deleteProperty_5(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/project/{projectIdOrKey}/properties/{propertyKey}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
