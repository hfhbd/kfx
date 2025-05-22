package com.jira.server

import com.jira.IssueSubTaskMovePositionBean
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
 * Reorder an issue's subtasks
 * Reorders an issue's subtasks by moving the subtask at index 'from' to index 'to'.
 */
public fun Route.moveSubTasks(action: suspend ApplicationCall.(IssueSubTaskMovePositionBean) -> Unit) {
  route(path = """/api/2/issue/{issueIdOrKey}/subtask/move""") {
    contentType(Json) {
      post {
        val body = call.receive<IssueSubTaskMovePositionBean>()
        call.action(body)
        call.respond(NoContent)
      }
    }
  }
}
