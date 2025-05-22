package com.jira.server

import com.jira.ProjectPickerResultWrapper
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get projects matching query
 * Returns a list of projects visible to the user where project name and/or key is matching the given query.
 * Passing an empty (or whitespace only) query will match no projects. The project matches will
 * contain a field with the query highlighted.
 * The number of projects returned can be controlled by passing a value for 'maxResults', but a hard limit of no
 * more than 100 projects is enforced. The projects are wrapped in a single response object that contains
 * a header for use in the picker, specifically 'Showing X of Y matching projects' and the total number
 * of matches for the query.
 */
public fun Route.searchForProjects(action: suspend ApplicationCall.() -> ProjectPickerResultWrapper) {
  route(path = """/api/2/projects/picker""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
