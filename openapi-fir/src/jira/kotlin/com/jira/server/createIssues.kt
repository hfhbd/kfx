package com.jira.server

import com.jira.IssuesCreateResponse
import com.jira.IssuesUpdateBean
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
 * Create an issue or sub-task from json - bulk operation.
 * Creates issues or sub-tasks from a JSON representation. Creates many issues in one bulk operation.
 * Creating a sub-task is similar to creating a regular issue. More details can be found in createIssue section.
 */
public fun Route.createIssues(action: suspend ApplicationCall.(IssuesUpdateBean) -> IssuesCreateResponse) {
  route(path = """/api/2/issue/bulk""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<IssuesUpdateBean>()
          val response = call.action(body)
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
