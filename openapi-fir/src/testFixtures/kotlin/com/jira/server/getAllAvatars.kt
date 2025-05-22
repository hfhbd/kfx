package com.jira.server

import com.jira.AvatarBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all avatars for a project
 * Returns all avatars which are visible for the currently logged in user. The avatars are grouped into system and custom.
 */
public fun Route.getAllAvatars(action: suspend ApplicationCall.() -> AvatarBean) {
  route(path = """/api/2/project/{projectIdOrKey}/avatars""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
