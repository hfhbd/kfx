package com.jira.server

import com.jira.AutoCompleteResultWrapper
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get auto complete suggestions for JQL search
 * Returns auto complete suggestions for JQL search
 */
public fun Route.getFieldAutoCompleteForQueryString(action: suspend ApplicationCall.() -> AutoCompleteResultWrapper) {
  route(path = """/api/2/jql/autocompletedata/suggestions""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
