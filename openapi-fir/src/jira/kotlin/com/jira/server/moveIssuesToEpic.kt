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
 * Move issues to a specific epic
 * Moves issues to an epic, for a given epic id. Issues can be only in a single epic at the same time. That means that already assigned issues to an epic, will not be assigned to the previous epic anymore. The user needs to have the edit issue permission for all issue they want to move and to the epic. The maximum number of issues that can be moved in one operation is 50.
 */
public fun Route.moveIssuesToEpic(action: suspend ApplicationCall.(IssueAssignRequestBean) -> Unit) {
  route(path = """/agile/1.0/epic/{epicIdOrKey}/issue""") {
    contentType(Json) {
      post {
        val body = call.receive<IssueAssignRequestBean>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
