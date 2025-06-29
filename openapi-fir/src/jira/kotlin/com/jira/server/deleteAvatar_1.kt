package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete avatar by ID
 * Deletes avatar
 */
public fun Route.deleteAvatar_1(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/universal_avatar/type/{type}/owner/{owningObjectId}/avatar/{id}""") {
    delete {
      call.action()
      call.respond(OK)
    }
  }
}
