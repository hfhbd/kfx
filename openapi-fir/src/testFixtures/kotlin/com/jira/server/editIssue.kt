package com.jira.server

import com.jira.IssueUpdateBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Edit an issue from a JSON representation
 * Edits an issue from a JSON representation. The issue can either be updated by setting explicit the field value(s) or by using an operation to change the field value.
 */
public fun Route.editIssue(action: suspend ApplicationCall.(IssueUpdateBean) -> Unit) {
  route(path = """/api/2/issue/{issueIdOrKey}""") {
    contentType(Json) {
      put {
        val body = call.receive<IssueUpdateBean>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
