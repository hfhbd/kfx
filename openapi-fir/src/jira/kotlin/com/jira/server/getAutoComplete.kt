package com.jira.server

import com.jira.AutoCompleteResponseBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get auto complete data for JQL searches
 * Returns the auto complete data required for JQL searches
 */
public fun Route.getAutoComplete(action: suspend ApplicationCall.() -> AutoCompleteResponseBean) {
  route(path = """/api/2/jql/autocompletedata""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
