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
 * Update issues to move them to the backlog
 * Move issues to the backlog. This operation is equivalent to remove future and active sprints from a given set of issues. At most 50 issues may be moved at once.
 */
public fun Route.moveIssuesToBacklog(action: suspend ApplicationCall.(IssueAssignRequestBean) -> Unit) {
  route(path = """/agile/1.0/backlog/issue""") {
    contentType(Json) {
      post {
        val body = call.receive<IssueAssignRequestBean>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
