package com.jira.server

import com.jira.IssueAssignRequestBean
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
 * Remove issues from any epic
 * Removes issues from epics. The user needs to have the edit issue permission for all issue they want to remove from epics. The maximum number of issues that can be moved in one operation is 50.
 */
public fun Route.removeIssuesFromEpic(action: suspend ApplicationCall.(IssueAssignRequestBean) -> Unit) {
  route(path = """/agile/1.0/epic/none/issue""") {
    contentType(Json) {
      post {
        val body = call.receive<IssueAssignRequestBean>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
