package com.jira.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.String
import kotlin.Unit

/**
 * Set the value of a specified user's property
 * Sets the value of the specified user's property.
 * You can use this resource to store a custom data against the user identified by the key or by the id. The user
 * who stores the data is required to have permissions to administer the user.
 */
public fun Route.setProperty_5(action: suspend ApplicationCall.(String) -> Unit) {
  route(path = """/api/2/user/properties/{propertyKey}""") {
    contentType(Json) {
      put {
        val body = call.receive<String>()
        call.action(body)
        call.respond(OK)
      }
    }
  }
}
