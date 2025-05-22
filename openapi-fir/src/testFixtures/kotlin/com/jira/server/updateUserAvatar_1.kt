package com.jira.server

import com.jira.AvatarBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route

/**
 * Update user avatar
 * Updates the avatar for the user.
 */
public fun Route.updateUserAvatar_1(action: suspend ApplicationCall.(AvatarBean) -> AvatarBean) {
  route(path = """/api/2/user/avatar""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<AvatarBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
