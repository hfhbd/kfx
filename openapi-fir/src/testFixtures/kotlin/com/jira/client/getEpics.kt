package com.jira.client

import com.jira.EpicBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Get all epics from the board
 * Returns all epics from the board, for the given board Id. This only includes epics that the user has permission to view. Note, if the user does not have permission to view the board, no epics will be returned at all.
 */
public suspend fun HttpClient.getEpics(
  boardId: Long,
  maxResults: Int? = null,
  done: String? = null,
  startAt: Long? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): EpicBean? {
  val response = `get`(urlString = """agile/1.0/board/${boardId}/epic""") {
    parameter("maxResults", maxResults)
    parameter("done", done)
    parameter("startAt", startAt)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<EpicBean>()
  return output
}
