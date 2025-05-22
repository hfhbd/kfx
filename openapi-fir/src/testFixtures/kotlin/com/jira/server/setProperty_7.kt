package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Set a property on a dashboard item
 * Sets the value of the property with a given key on the dashboard item identified by the id.
 */
public fun Route.setProperty_7(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/dashboard/{dashboardId}/items/{itemId}/properties/{propertyKey}""") {
    put {
      call.action()
      call.respond(OK)
    }
  }
}
