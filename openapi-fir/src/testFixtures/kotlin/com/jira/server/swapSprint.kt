package com.jira.server

import com.jira.SprintSwapBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlin.Unit

/**
 * Swap the position of two sprints
 * Swap the position of the sprint with the second sprint.
 */
public fun Route.swapSprint(action: suspend ApplicationCall.(SprintSwapBean) -> Unit) {
  route(path = """/agile/1.0/sprint/{sprintId}/swap""") {
    contentType(Json) {
      post {
        val body = call.receive<SprintSwapBean>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
