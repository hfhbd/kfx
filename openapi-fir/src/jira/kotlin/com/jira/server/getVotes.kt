package com.jira.server

import com.jira.VoteBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get votes for issue
 * A REST sub-resource representing the voters on the issue.
 */
public fun Route.getVotes(action: suspend ApplicationCall.() -> VoteBean) {
  route(path = """/api/2/issue/{issueIdOrKey}/votes""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
