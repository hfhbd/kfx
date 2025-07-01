package com.jira.server

import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Delete avatar
 * Deletes avatar
 */
public fun Route.deleteAvatar_2(action: suspend ApplicationCall.() -> Unit) {
  route(path = """/api/2/user/avatar/{id}""") {
    delete {
      call.action()
      call.respond(NoContent)
    }
  }
}
