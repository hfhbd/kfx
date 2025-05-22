package com.jira.server

import com.jira.EntityPropertiesKeysBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all properties keys for issue type
 * Returns the keys of all properties for the issue type identified by the id
 */
public fun Route.getPropertyKeys(action: suspend ApplicationCall.() -> EntityPropertiesKeysBean) {
  route(path = """/api/2/issuetype/{issueTypeId}/properties""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
