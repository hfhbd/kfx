package com.jira.server

import com.jira.IssueCreateResponse
import com.jira.IssueUpdateBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Create an issue or sub-task from json
 * Creates an issue or a sub-task from a JSON representation.
 * The fields that can be set on create, in either the fields parameter or the update parameter can be determined using the /rest/api/2/issue/createmeta resource.
 * If a field is not configured to appear on the create screen, then it will not be in the createmeta, and a field
 * validation error will occur if it is submitted.
 * Creating a sub-task is similar to creating a regular issue, with two important differences:
 * - the issueType field must correspond to a sub-task issue type (you can use /issue/createmeta to discover sub-task issue types), and
 * - you must provide a parent field in the issue create request containing the id or key of the parent issue.
 * The updateHistory param adds the project that this issue is created in, to the current user's project history, if set to true (by default, the project history is not updated).
 * You can view the project history in the Jira application, via the Projects dropdown.
 */
public fun Route.createIssue(action: suspend ApplicationCall.(IssueUpdateBean) -> IssueCreateResponse) {
  route(path = """/api/2/issue""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<IssueUpdateBean>()
          val response = call.action(body)
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
