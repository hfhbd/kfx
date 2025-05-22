package com.jira.server

import com.jira.FieldEditBean
import com.jira.FieldValueBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route

/**
 * Update the estimation of an issue for a board
 * Updates the estimation of the issue. boardId param is required. This param determines which field will be updated on a issue.
 * Note that this resource changes the estimation field of the issue regardless of appearance the field on the screen.
 * Original time tracking estimation field accepts estimation in formats like "1w", "2d", "3h", "20m" or number which represent number of minutes.
 * However, internally the field stores and returns the estimation as a number of seconds.
 * The field used for estimation on the given board can be obtained from <a href="#agile/1.0/board-getConfiguration">board configuration resource</a>.
 * More information about the field are returned by edit meta resource or field resource.
 */
public fun Route.estimateIssueForBoard(action: suspend ApplicationCall.(FieldEditBean) -> FieldValueBean) {
  route(path = """/agile/1.0/issue/{issueIdOrKey}/estimation""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<FieldEditBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
