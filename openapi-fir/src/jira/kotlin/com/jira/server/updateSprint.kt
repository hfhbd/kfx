package com.jira.server

import com.jira.SprintBean
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
 * Update a sprint fully
 * Performs a full update of a sprint.
 * A full update means that the result will be exactly the same as the request body.
 * Any fields not present in the request JSON will be set to null.
 * Notes:
 * - Sprints that are in a closed state cannot be updated.
 * - A sprint can be started by updating the state to 'active'. This requires the sprint to be in the 'future' state and have a startDate and endDate set.
 * - A sprint can be completed by updating the state to 'closed'. This action requires the sprint to be in the 'active' state. This sets the completeDate to the time of the request.
 * - Other changes to state are not allowed.
 * - The completeDate field cannot be updated manually.
 * - Only Jira administrators can edit dates on sprints that are marked as synced.
 */
public fun Route.updateSprint(action: suspend ApplicationCall.(SprintBean) -> SprintBean) {
  route(path = """/agile/1.0/sprint/{sprintId}""") {
    contentType(Json) {
      accept(Json) {
        put {
          val body = call.receive<SprintBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
