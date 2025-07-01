package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.Long
import kotlin.Unit

/**
 * Delete the board
 * Deletes the board.
 */
public suspend fun HttpClient.deleteBoard(boardId: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """agile/1.0/board/${boardId}""") {
    builder()
  }
}
