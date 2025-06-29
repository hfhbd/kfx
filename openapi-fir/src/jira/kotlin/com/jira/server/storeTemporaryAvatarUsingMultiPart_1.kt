package com.jira.server

import com.jira.AvatarCroppingBean
import io.ktor.http.ContentType
import io.ktor.http.ContentType.MultiPart.FormData
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Store temporary avatar using multipart
 * Creates temporary avatar using multipart. The response is sent back as JSON stored in a textarea. This is because
 * the client uses remote iframing to submit avatars using multipart. So we must send them a valid HTML page back from
 * which the client parses the JSON.
 */
public fun Route.storeTemporaryAvatarUsingMultiPart_1(action: suspend ApplicationCall.() -> AvatarCroppingBean) {
  route(path = """/api/2/project/{projectIdOrKey}/avatar/temporary""") {
    contentType(FormData) {
      accept(ContentType.parse("text/html")) {
        post {
          val response = call.action()
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
