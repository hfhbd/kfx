package com.jira.server

import com.jira.EntityPropertyBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get the value of a specific property from an issue
 * Returns the value of the property with a given key from the issue identified by the key or by the id.
 */
public fun Route.getProperty_3(action: suspend ApplicationCall.() -> EntityPropertyBean) {
  route(path = """/api/2/issue/{issueIdOrKey}/properties/{propertyKey}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
