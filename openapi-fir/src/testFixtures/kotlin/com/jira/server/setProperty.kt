package com.jira.server

import com.jira.EntityPropertiesKeysBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.put
import io.ktor.server.routing.route

/**
 * Update a board's property
 * Sets the value of the specified board's property. You can use this resource to store a custom data against the board identified by the id. The user who stores the data is required to have permissions to modify the board.
 */
public fun Route.setProperty(action: suspend ApplicationCall.() -> EntityPropertiesKeysBean) {
  route(path = """/agile/1.0/board/{boardId}/properties/{propertyKey}""") {
    accept(Json) {
      put {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
