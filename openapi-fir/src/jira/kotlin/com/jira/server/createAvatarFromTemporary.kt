package com.jira.server

import com.jira.AvatarCroppingBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Update avatar cropping
 * Updates the cropping instructions of the temporary avatar
 */
public fun Route.createAvatarFromTemporary(action: suspend ApplicationCall.(AvatarCroppingBean) -> Unit) {
  route(path = """/api/2/avatar/{type}/temporaryCrop""") {
    contentType(Json) {
      post {
        val body = call.receive<AvatarCroppingBean>()
        call.action(body)
        call.respond(OK)
      }
    }
  }
}
