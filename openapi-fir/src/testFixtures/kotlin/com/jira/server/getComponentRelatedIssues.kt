package com.jira.server

import com.jira.ComponentIssueCountsBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get component related issues
 * Returns counts of issues related to this component.
 */
public fun Route.getComponentRelatedIssues(action: suspend ApplicationCall.() -> ComponentIssueCountsBean) {
  route(path = """/api/2/component/{id}/relatedIssueCounts""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
