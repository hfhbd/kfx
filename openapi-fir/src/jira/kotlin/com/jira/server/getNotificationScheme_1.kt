package com.jira.server

import com.jira.NotificationSchemeBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get notification scheme associated with the project
 * Gets a notification scheme associated with the project. Follow the documentation of /notificationscheme/{id} resource for all details about returned value.
 */
public fun Route.getNotificationScheme_1(action: suspend ApplicationCall.() -> NotificationSchemeBean) {
  route(path = """/api/2/project/{projectKeyOrId}/notificationscheme""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
