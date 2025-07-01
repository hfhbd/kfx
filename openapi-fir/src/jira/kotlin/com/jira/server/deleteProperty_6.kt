package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete a specified user's property
 * Removes the property from the user identified by the key or by the id. The user who removes the property is required to have permissions to administer the user.
 */
public fun Route.deleteProperty_6(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/user/properties/{propertyKey}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
