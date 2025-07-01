package com.jira.server

import com.jira.UserAnonymizationRerunRequestBean
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
 * Schedule user anonymization rerun
 * Schedules a user anonymization process. Requires system admin permission.
 */
public fun Route.scheduleUserAnonymizationRerun(action: suspend ApplicationCall.(UserAnonymizationRerunRequestBean) -> Unit) {
  route(path = """/api/2/user/anonymization/rerun""") {
    contentType(Json) {
      post {
        val body = call.receive<UserAnonymizationRerunRequestBean>()
        call.action(body)
        call.respond(Accepted)
      }
    }
  }
}
