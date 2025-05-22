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
 * Get all avatars for a type and owner
 * Returns a list of all avatars
 */
public fun Route.getAvatars(action: suspend ApplicationCall.() -> AvatarBean) {
  route(path = """/api/2/universal_avatar/type/{type}/owner/{owningObjectId}""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
