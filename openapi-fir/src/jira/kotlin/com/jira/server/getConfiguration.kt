package com.jira.server

import com.jira.BoardConfigBean
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.`get`
import io.ktor.server.routing.accept
import io.ktor.server.routing.route

/**
 * Get the board configuration
 * Get the board configuration.
 * The response contains the following fields:
 * - id - Id of the board.
 * - name - Name of the board.
 * - filter - Reference to the filter used by the given board.
 * - subQuery (Kanban only) - JQL subquery used by the given board.
 * - columnConfig - The column configuration lists the columns for the board, in the order defined in the column configuration.
 * For each column, it shows the issue status mapping
 * as well as the constraint type (Valid values: none, issueCount, issueCountExclSubs) for the min/max number of issues.
 * Note, the last column with statuses mapped to it is treated as the "Done" column,
 * which means that issues in that column will be marked as already completed.
 * - estimation (Scrum only) - Contains information about type of estimation used for the board. Valid values: none, issueCount, field.
 * If the estimation type is "field", the Id and display name of the field used for estimation is also returned.
 * Note, estimates for an issue can be updated by a PUT /rest/api/2/issue/{issueIdOrKey} request, however the fields must be on the screen.
 * "timeoriginalestimate" field will never be on the screen, so in order to update it "originalEstimate" in "timetracking" field should be updated.
 * - ranking - Contains information about custom field used for ranking in the given board.
 */
public fun Route.getConfiguration(action: suspend ApplicationCall.() -> BoardConfigBean) {
  route(path = """/agile/1.0/board/{boardId}/configuration""") {
    accept(Json) {
      `get` {
        val response = call.action()
        call.response.status(OK)
        call.respond(response)
      }
    }
  }
}
