package com.jira.server

import com.jira.PageBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get paginated project versions
 * Returns all versions for the specified project. Results are paginated. Results can be ordered by the following fields: sequence, name, startDate, releaseDate.
 */
public fun Route.getProjectVersionsPaginated(action: suspend ApplicationCall.() -> PageBean) {
  route(path = """/api/2/project/{projectIdOrKey}/version""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
