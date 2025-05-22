package com.jira.server

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.String
import kotlin.Unit

/**
 * Add a user as watcher
 * Adds a user to an issue's watcher list.
 */
public fun Route.addWatcher_1(action: suspend ApplicationCall.(String) -> Unit) {
  route(path = """/api/2/issue/{issueIdOrKey}/watchers""") {
    contentType(Json) {
      post {
        val body = call.receive<String>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
