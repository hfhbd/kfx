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
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Partially update a sprint
 * Performs a partial update of a sprint.
 * A partial update means that fields not present in the request JSON will not be updated.
 * Notes:
 * - Sprints that are in a closed state cannot be updated.
 * - A sprint can be started by updating the state to 'active'. This requires the sprint to be in the 'future' state and have a startDate and endDate set.
 * - A sprint can be completed by updating the state to 'closed'. This action requires the sprint to be in the 'active' state. This sets the completeDate to the time of the request.
 * - Other changes to state are not allowed.
 * - The completeDate field cannot be updated manually.
 * - Sprint goal can be removed by updating it's value to empty string
 * - Only Jira administrators can edit dates on sprints that are marked as synced.
 */
public fun Route.partiallyUpdateSprint(action: suspend ApplicationCall.(SprintBean) -> SprintBean) {
  route(path = """/agile/1.0/sprint/{sprintId}""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<SprintBean>()
          val response = call.action(body)
          call.response.status(OK)
          call.respond(response)
        }
      }
    }
  }
}
