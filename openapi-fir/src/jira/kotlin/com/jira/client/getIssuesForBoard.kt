package com.jira.client

import com.jira.IssueBean
import com.jira.StringList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.collections.List

/**
 * Get all issues from a board
 * Returns all issues from a board, for a given board Id. This only includes issues that the user has permission to view. Note, if the user does not have permission to view the board, no issues will be returned at all. Issues returned from this resource include Agile fields, like sprint, closedSprints, flagged, and epic. By default, the returned issues are ordered by rank.
 */
public suspend fun HttpClient.getIssuesForBoard(
  boardId: Long,
  expand: String? = null,
  jql: String? = null,
  maxResults: Int? = null,
  validateQuery: Boolean? = null,
  fields: List<StringList>? = null,
  startAt: Long? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): IssueBean? {
  val response = `get`(urlString = """agile/1.0/board/${boardId}/issue""") {
    parameter("expand", expand)
    parameter("jql", jql)
    parameter("maxResults", maxResults)
    parameter("validateQuery", validateQuery)
    parameter("fields", fields)
    parameter("startAt", startAt)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<IssueBean>()
  return output
}
