package com.jira.client

import com.jira.SprintBean
import com.jira.StringList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Int
import kotlin.Long
import kotlin.Unit

/**
 * Get all sprints from a board
 * Returns all sprints from a board, for a given board Id. This only includes sprints that the user has permission to view.
 */
public suspend fun HttpClient.getAllSprints(
  boardId: Long,
  maxResults: Int? = null,
  state: StringList? = null,
  startAt: Long? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): SprintBean? {
  val response = `get`(urlString = """agile/1.0/board/${boardId}/sprint""") {
    parameter("maxResults", maxResults)
    parameter("state", state)
    parameter("startAt", startAt)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<SprintBean>()
  return output
}
