package com.jira.server

import com.jira.PageBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get paginated notification schemes
 * Returns a paginated list of notification schemes. In order to access notification scheme, the calling user is
 * required to have permissions to administer at least one project associated with the requested notification scheme. Each scheme contains
 * a list of events and recipient configured to receive notifications for these events. Consumer should allow events without recipients to appear in response.
 * The list is ordered by the scheme's name.
 * Follow the documentation of /notificationscheme/{id} resource for all details about returned value.
 */
public fun Route.getNotificationSchemes(action: suspend ApplicationCall.() -> PageBean) {
  route(path = """/api/2/notificationscheme""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
