package com.jira.server

import com.jira.SprintBean
import com.jira.SprintCreateBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.accept
import io.ktor.server.routing.contentType
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * Create a future sprint
 * Creates a future sprint. Sprint name and origin board id are required. Start and end date are optional. Notes: The sprint name is trimmed. Only Jira administrators can create synced sprints.
 */
public fun Route.createSprint(action: suspend ApplicationCall.(SprintCreateBean) -> SprintBean) {
  route(path = """/agile/1.0/sprint""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<SprintCreateBean>()
          val response = call.action(body)
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
