package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete a property from a dashboard item
 * Removes the property from the dashboard item identified by the key or by the id.
 */
public fun Route.deleteProperty_1(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/dashboard/{dashboardId}/items/{itemId}/properties/{propertyKey}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
