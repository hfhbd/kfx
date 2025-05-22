package com.jira.server

import com.jira.UserAnonymizationRequestBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Accepted
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Schedule user anonymization
 * Schedules a user anonymization process. Requires system admin permission.
 */
public fun Route.scheduleUserAnonymization(action: suspend ApplicationCall.(UserAnonymizationRequestBean) -> Unit) {
  route(path = """/api/2/user/anonymization""") {
    contentType(Json) {
      post {
        val body = call.receive<UserAnonymizationRequestBean>()
        call.action(body)
        call.respond(Accepted)
      }
    }
  }
}
