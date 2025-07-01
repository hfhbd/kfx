package com.jira.server

import com.jira.IssueLinkTypesBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get list of available issue link types
 * Returns a list of available issue link types, if issue linking is enabled.
 */
public fun Route.getIssueLinkTypes(action: suspend ApplicationCall.() -> IssueLinkTypesBean) {
  route(path = """/api/2/issueLinkType""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
