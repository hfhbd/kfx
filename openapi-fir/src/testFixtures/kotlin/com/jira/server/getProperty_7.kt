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
 * Get value of specified issue type's property
 * Returns the value of the property with a given key from the issue type identified by the id
 */
public fun Route.getProperty_7(action: suspend ApplicationCall.() -> EntityPropertyBean) {
  route(path = """/api/2/issuetype/{issueTypeId}/properties/{propertyKey}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
