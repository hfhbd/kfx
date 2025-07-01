package com.jira.server

import com.jira.AvatarBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Update project avatar
 * Updates an avatar for a project. This is step 3/3 of changing an avatar for a project.
 */
public fun Route.updateProjectAvatar(action: suspend ApplicationCall.(AvatarBean) -> Unit) {
  route(path = """/api/2/project/{projectIdOrKey}/avatar""") {
    contentType(Json) {
      put {
        val body = call.receive<AvatarBean>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
