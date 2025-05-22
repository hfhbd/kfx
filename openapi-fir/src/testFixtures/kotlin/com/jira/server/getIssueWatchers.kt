package com.jira.server

import com.jira.WatchersBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get list of watchers of issue
 * Returns the list of watchers for the issue with the given key.
 */
public fun Route.getIssueWatchers(action: suspend ApplicationCall.() -> WatchersBean) {
  route(path = """/api/2/issue/{issueIdOrKey}/watchers""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
