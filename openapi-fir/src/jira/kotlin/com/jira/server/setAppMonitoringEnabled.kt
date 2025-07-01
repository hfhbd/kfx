package com.jira.server

import com.jira.AppMonitoringRestEntity
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
 * Update App Monitoring status
 * Enables or disables App Monitoring
 */
public fun Route.setAppMonitoringEnabled(action: suspend ApplicationCall.(AppMonitoringRestEntity) -> Unit) {
  route(path = """/api/2/monitoring/app""") {
    contentType(Json) {
      post {
        val body = call.receive<AppMonitoringRestEntity>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
