package com.jira.server

import com.jira.ScreenableTabBean
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
 * Rename a tab on a screen
 * Renames tab on given screen.
 */
public fun Route.renameTab(action: suspend ApplicationCall.(ScreenableTabBean) -> ScreenableTabBean) {
  route(path = """/api/2/screens/{screenId}/tabs/{tabId}""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<ScreenableTabBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
