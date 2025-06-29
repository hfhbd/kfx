package com.jira.server

import com.jira.AvatarBean
import com.jira.AvatarCroppingBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Convert temporary avatar into a real avatar
 * Converts temporary avatar into a real avatar
 */
public fun Route.createAvatarFromTemporary_1(action: suspend ApplicationCall.(AvatarCroppingBean) -> AvatarBean) {
  route(path = """/api/2/issuetype/{id}/avatar""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<AvatarCroppingBean>()
          val response = call.action(body)
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
