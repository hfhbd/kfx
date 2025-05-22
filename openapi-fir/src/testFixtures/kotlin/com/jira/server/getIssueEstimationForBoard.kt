package com.jira.server

import com.jira.FieldValueBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get the estimation of an issue for a board
 * Returns the estimation of the issue and a fieldId of the field that is used for it.
 * Original time internally stores and returns the estimation as a number of seconds.
 * The field used for estimation on the given board can be obtained from board configuration resource.
 * More information about the field are returned by edit meta resource or field resource.
 */
public fun Route.getIssueEstimationForBoard(action: suspend ApplicationCall.() -> FieldValueBean) {
  route(path = """/agile/1.0/issue/{issueIdOrKey}/estimation""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
