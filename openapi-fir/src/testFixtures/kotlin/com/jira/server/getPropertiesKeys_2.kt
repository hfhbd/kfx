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
 * Get keys of all properties for an issue
 * Returns the keys of all properties for the issue identified by the key or by the id.
 */
public fun Route.getPropertiesKeys_2(action: suspend ApplicationCall.() -> EntityPropertiesKeysBean) {
  route(path = """/api/2/issue/{issueIdOrKey}/properties""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
