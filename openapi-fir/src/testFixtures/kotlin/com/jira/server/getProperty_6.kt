package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Get the value of a specified user's property
 * Returns the value of the property with a given key from the user identified by the key or by the id.
 */
public fun Route.getProperty_6(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/user/properties/{propertyKey}""") {
    `get` {
      call.action()
      call.respond(OK)
    }
  }
}
