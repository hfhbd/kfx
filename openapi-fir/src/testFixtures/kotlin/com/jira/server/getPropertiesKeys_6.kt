package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Get keys of all properties for a user
 * Returns the keys of all properties for the user identified by the key or by the id.
 */
public fun Route.getPropertiesKeys_6(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/user/properties""") {
    `get` {
      call.action()
      call.respond(OK)
    }
  }
}
