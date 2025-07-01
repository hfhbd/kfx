package com.jira.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.delete
import io.ktor.server.routing.route
import kotlin.String
import kotlin.Unit

/**
 * Invalidate the current WebSudo session
 * This method invalidates the any current WebSudo session.
 */
public fun Route.release(action: suspend ApplicationCall.(String) -> Unit) {
  route(path = """/auth/1/websudo""") {
    contentType(Json) {
      delete {
        val body = call.receive<String>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
