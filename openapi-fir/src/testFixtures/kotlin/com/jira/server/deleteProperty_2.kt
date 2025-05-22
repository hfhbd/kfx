package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete a property from a comment
 * Removes the property from the comment identified by the key or by the id. Ths user removing the property is required to have permissions to administer the comment.
 */
public fun Route.deleteProperty_2(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/comment/{commentId}/properties/{propertyKey}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
