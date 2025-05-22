package com.jira.server

import com.jira.AvatarCroppingBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.ContentType.MultiPart.FormData
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Create temporary avatar using multipart upload
 * Creates temporary avatar
 */
public fun Route.storeTemporaryAvatarUsingMultiPart_2(action: suspend ApplicationCall.() -> AvatarCroppingBean) {
  route(path = """/api/2/universal_avatar/type/{type}/owner/{owningObjectId}/temp""") {
    contentType(FormData) {
      accept(Json) {
        post {
          val response = call.action()
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
