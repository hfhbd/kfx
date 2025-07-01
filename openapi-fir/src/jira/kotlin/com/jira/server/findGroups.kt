package com.jira.server

import com.jira.GroupSuggestionsBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get groups matching a query
 * Returns groups with substrings matching a given query
 */
public fun Route.findGroups(action: suspend ApplicationCall.() -> GroupSuggestionsBean) {
  route(path = """/api/2/groups/picker""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
