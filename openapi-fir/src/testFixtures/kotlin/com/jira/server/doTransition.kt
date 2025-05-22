package com.jira.server

import com.jira.IssueUpdateBean
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
 * Perform a transition on an issue
 * Perform a transition on an issue.
 * When performing the transition you can update or set other issue fields.
 * The fields that can be set on transition, in either the fields parameter or the update parameter can be determined using the /rest/api/2/issue/{issueIdOrKey}/transitions?expand=transitions.fields resource.
 * If a field is not configured to appear on the transition screen, then it will not be in the transition metadata, and a field validation error will occur if it is submitted.
 * The updateHistory param adds the issues retrieved by this method to the current user's issue history, if set to true (by default, the issue history does not include issues retrieved via the REST API).
 * You can view the issue history in the Jira application, via the Issues dropdown or by using the lastViewed JQL field in an issue search.
 */
public fun Route.doTransition(action: suspend ApplicationCall.(IssueUpdateBean) -> Unit) {
  route(path = """/api/2/issue/{issueIdOrKey}/transitions""") {
    contentType(Json) {
      post {
        val body = call.receive<IssueUpdateBean>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
