package com.jira.client

import com.jira.SprintBean
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
 * Get all issues for a sprint
 * Get all issues you have access to that belong to the sprint from the board. Issue returned from this resource contains additional fields like: sprint, closedSprints, flagged and epic. Issues are returned ordered by rank. JQL order has higher priority than default rank.
 */
public suspend fun HttpClient.getIssuesForSprint(
  sprintId: Long,
  boardId: Long,
  expand: String? = null,
  jql: String? = null,
  maxResults: Int? = null,
  validateQuery: Boolean? = null,
  fields: List<StringList>? = null,
  startAt: Long? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): SprintBean? {
  val response = `get`(urlString = """agile/1.0/board/${boardId}/sprint/${sprintId}/issue""") {
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
  val output = response.body<SprintBean>()
  return output
}
