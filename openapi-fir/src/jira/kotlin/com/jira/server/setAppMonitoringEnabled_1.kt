package com.jira.server

import com.jira.IpdMonitoringRestEntity
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
 * Update IPD Monitoring status
 * Enables or disables IPD Monitoring
 */
public fun Route.setAppMonitoringEnabled_1(action: suspend ApplicationCall.(IpdMonitoringRestEntity) -> Unit) {
  route(path = """/api/2/monitoring/ipd""") {
    contentType(Json) {
      post {
        val body = call.receive<IpdMonitoringRestEntity>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
