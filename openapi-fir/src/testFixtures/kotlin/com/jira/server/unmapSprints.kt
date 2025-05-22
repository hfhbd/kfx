package com.jira.server

import com.jira.UnmapSprintsBean
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
 * Unmap sprints from being synced
 * Sets the Synced flag to false for all sprints in the provided list.
 */
public fun Route.unmapSprints(action: suspend ApplicationCall.(UnmapSprintsBean) -> Unit) {
  route(path = """/agile/1.0/sprint/unmap""") {
    contentType(Json) {
      put {
        val body = call.receive<UnmapSprintsBean>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
