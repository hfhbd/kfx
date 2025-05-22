package com.jira.client

import com.jira.BoardConfigBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Long
import kotlin.Unit

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
public suspend fun HttpClient.getConfiguration(boardId: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}): BoardConfigBean? {
  val response = `get`(urlString = """agile/1.0/board/${boardId}/configuration""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<BoardConfigBean>()
  return output
}
