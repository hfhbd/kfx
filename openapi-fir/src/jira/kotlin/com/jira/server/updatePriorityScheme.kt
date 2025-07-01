package com.jira.server

import com.jira.PrioritySchemeBean
import com.jira.PrioritySchemeUpdateBean
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
 * Update a priority scheme
 * Updates a priority scheme. Update will be rejected if issue migration would be needed as a result of scheme update. Priority scheme update with migration is possible from the UI.
 */
public fun Route.updatePriorityScheme(action: suspend ApplicationCall.(PrioritySchemeUpdateBean) -> PrioritySchemeBean) {
  route(path = """/api/2/priorityschemes/{schemeId}""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<PrioritySchemeUpdateBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
