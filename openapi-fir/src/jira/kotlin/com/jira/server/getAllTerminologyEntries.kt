package com.jira.server

import com.jira.TerminologyResponseBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all defined names for 'epic' and 'sprint'
 * Returns a list of all defined names for the default words 'epic' and 'sprint'
 */
public fun Route.getAllTerminologyEntries(action: suspend ApplicationCall.() -> TerminologyResponseBean) {
  route(path = """/api/2/terminology/entries""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
