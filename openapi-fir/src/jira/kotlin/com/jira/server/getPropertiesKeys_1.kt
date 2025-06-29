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
 * Get all properties keys for a sprint
 * Returns the keys of all properties for the sprint identified by the id. The user who retrieves the property keys is required to have permissions to view the sprint.
 */
public fun Route.getPropertiesKeys_1(action: suspend ApplicationCall.() -> EntityPropertiesKeysBean) {
  route(path = """/agile/1.0/sprint/{sprintId}/properties""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
