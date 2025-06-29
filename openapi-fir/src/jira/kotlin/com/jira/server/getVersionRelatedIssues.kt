package com.jira.server

import com.jira.VersionIssueCountsBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get version related issues count
 * Returns a bean containing the number of fixed in and affected issues for the given version.
 */
public fun Route.getVersionRelatedIssues(action: suspend ApplicationCall.() -> VersionIssueCountsBean) {
  route(path = """/api/2/version/{id}/relatedIssueCounts""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
