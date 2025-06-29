package com.jira.server

import com.jira.BoardBean
import com.jira.BoardCreateBean
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
 * Create a new board
 * Creates a new board. Board name, type and filter Id is required.
 * - name - Must be less than 255 characters.
 * - type - Valid values: scrum, kanban
 * - filterId - Id of a filter that the user has permissions to view. Note, if the user does not have the 'Create shared objects' permission and tries to create a shared board, a private board will be created instead (remember that board sharing depends on the filter sharing).
 * Note:
 * - If you want to create a new project with an associated board, use the JIRA platform REST API. For more information, see the Create project method. The projectTypeKey for software boards must be 'software' and the projectTemplateKey must be either com.pyxis.greenhopper.jira:gh-kanban-template or com.pyxis.greenhopper.jira:gh-scrum-template.
 * - You can create a filter using the JIRA REST API. For more information, see the Create filter method.
 * - If you do not ORDER BY the Rank field for the filter of your board, you will not be able to reorder issues on the board.
 */
public fun Route.createBoard(action: suspend ApplicationCall.(BoardCreateBean) -> BoardBean) {
  route(path = """/agile/1.0/board""") {
    contentType(Json) {
      accept(Json) {
        post {
          val body = call.receive<BoardCreateBean>()
          val response = call.action(body)
          call.response.status(Created)
          call.respond(response)
        }
      }
    }
  }
}
