package com.jira.server

import com.jira.ProjectJsonBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get all projects associated with the board
 * Returns all projects that are associated with the board, for the given board Id. A project is associated with a board only if the board filter explicitly filters issues by the project and guaranties that all issues will come for one of those projects e.g. board's filter with "project in (PR-1, PR-1) OR reporter = admin" jql Projects are returned only if user can browse all projects that are associated with the board. Note, if the user does not have permission to view the board, no projects will be returned at all. Returned projects are ordered by the name.
 */
public fun Route.getProjects(action: suspend ApplicationCall.() -> ProjectJsonBean) {
  route(path = """/agile/1.0/board/{boardId}/project""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
