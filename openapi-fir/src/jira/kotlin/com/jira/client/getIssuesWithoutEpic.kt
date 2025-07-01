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
 * Get all issues without an epic
 * Returns all issues that do not belong to any epic on a board, for a given board Id.
 */
public suspend fun HttpClient.getIssuesWithoutEpic(
  boardId: Long,
  expand: String? = null,
  jql: String? = null,
  maxResults: Int? = null,
  validateQuery: Boolean? = null,
  fields: List<StringList>? = null,
  startAt: Long? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): IssueBean? {
  val response = `get`(urlString = """agile/1.0/board/${boardId}/epic/none/issue""") {
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
