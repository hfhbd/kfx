package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete specified issue type's property
 * Removes the property from the issue type identified by the id
 */
public fun Route.deleteProperty_4(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/issuetype/{issueTypeId}/properties/{propertyKey}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
