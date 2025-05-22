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
 * Get properties keys of a comment
 * Returns the keys of all properties for the comment identified by the key or by the id.
 */
public fun Route.getPropertiesKeys_4(action: suspend ApplicationCall.() -> EntityPropertiesKeysBean) {
  route(path = """/api/2/comment/{commentId}/properties""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
