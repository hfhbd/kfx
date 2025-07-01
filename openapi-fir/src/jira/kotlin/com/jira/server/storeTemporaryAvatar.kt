package com.jira.server

import com.jira.AvatarCroppingBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Create temporary avatar
 * Creates temporary avatar
 */
public fun Route.storeTemporaryAvatar(action: suspend ApplicationCall.() -> AvatarCroppingBean) {
  route(path = """/api/2/avatar/{type}/temporary""") {
    accept(Json) {
      post {
        val response = call.action()
        call.response.status(Created)
        call.respond(response)
      }
    }
  }
}
