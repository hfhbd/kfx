package com.jira.server

import com.jira.MoveFieldBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Move field on a tab
 * Moves field on the given tab.
 */
public fun Route.moveField(action: suspend ApplicationCall.(MoveFieldBean) -> Unit) {
  route(path = """/api/2/screens/{screenId}/tabs/{tabId}/fields/{id}/move""") {
    contentType(Json) {
      post {
        val body = call.receive<MoveFieldBean>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
