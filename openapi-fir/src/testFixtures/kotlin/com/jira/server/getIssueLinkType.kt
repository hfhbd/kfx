package com.jira.server

import com.jira.IssueLinkTypeJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get information about an issue link type
 * Returns for a given issue link type id all information about this issue link type.
 */
public fun Route.getIssueLinkType(action: suspend ApplicationCall.() -> IssueLinkTypeJsonBean) {
  route(path = """/api/2/issueLinkType/{issueLinkTypeId}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
