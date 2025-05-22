package com.jira.client

import com.jira.BoardBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Long
import kotlin.Unit

/**
 * Get a single board
 * Returns a single board, for a given board Id.
 */
public suspend fun HttpClient.getBoard(boardId: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}): BoardBean? {
  val response = `get`(urlString = """agile/1.0/board/${boardId}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<BoardBean>()
  return output
}
