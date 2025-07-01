package com.jira.server

import com.jira.NotificationJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Send notification to recipients
 * Sends a notification (email) to the list or recipients defined in the request.
 */
public fun Route.notify(action: suspend ApplicationCall.(NotificationJsonBean) -> Unit) {
  route(path = """/api/2/issue/{issueIdOrKey}/notify""") {
    contentType(Json) {
      post {
        val body = call.receive<NotificationJsonBean>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
