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
 * Get epic or sprint name by original name
 * Returns epic or sprint name as specified in the {originalName} path param
 */
public fun Route.getTerminologyEntry(action: suspend ApplicationCall.() -> TerminologyResponseBean) {
  route(path = """/api/2/terminology/entries/{originalName}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
