package com.jira.server

import com.jira.IdBean
import com.jira.PrioritySchemeBean
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
 * Assign project with priority scheme
 * Assigns project with priority scheme. Priority scheme assign with migration is possible from the UI. Operation will fail if migration is needed as a result of operation eg. there are issues with priorities invalid in the destination scheme. All project keys associated with the priority scheme will only be returned if additional query parameter is provided expand=projectKeys.
 */
public fun Route.assignPriorityScheme(action: suspend ApplicationCall.(IdBean) -> PrioritySchemeBean) {
  route(path = """/api/2/project/{projectKeyOrId}/priorityscheme""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<IdBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
