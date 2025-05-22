package com.jira.server

import com.jira.AddFieldBean
import com.jira.ScreenableFieldBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Add field to a tab
 * Adds field to the given tab.
 */
public fun Route.addField(action: suspend ApplicationCall.(AddFieldBean) -> ScreenableFieldBean) {
  route(path = """/api/2/screens/{screenId}/tabs/{tabId}/fields""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<AddFieldBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
