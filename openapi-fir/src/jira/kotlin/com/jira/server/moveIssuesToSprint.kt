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
 * Move issues to a sprint
 * Moves issues to a sprint, for a given sprint Id. Issues can only be moved to open or active sprints. The maximum number of issues that can be moved in one operation is 50.
 */
public fun Route.moveIssuesToSprint(action: suspend ApplicationCall.(IssueAssignRequestBean) -> Unit) {
  route(path = """/agile/1.0/sprint/{sprintId}/issue""") {
    contentType(Json) {
      post {
        val body = call.receive<IssueAssignRequestBean>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
