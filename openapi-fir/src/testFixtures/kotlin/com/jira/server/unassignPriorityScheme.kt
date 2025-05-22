package com.jira.server

import com.jira.PrioritySchemeBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.delete
import io.ktor.server.routing.route

/**
 * Unassign project from priority scheme
 * Unassigns project from priority scheme. Operation will fail for defualt priority scheme, project is not found or project is not associated with provided priority scheme. All project keys associated with the priority scheme will only be returned if additional query parameter is provided expand=projectKeys.
 */
public fun Route.unassignPriorityScheme(action: suspend ApplicationCall.() -> PrioritySchemeBean) {
  route(path = """/api/2/project/{projectKeyOrId}/priorityscheme/{schemeId}""") {
    accept(Json) {
      delete {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
