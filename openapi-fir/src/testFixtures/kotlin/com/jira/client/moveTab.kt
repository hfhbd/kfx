package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import kotlin.Int
import kotlin.Long
import kotlin.Unit

/**
 * Move tab position
 * Moves tab position.
 */
public suspend fun HttpClient.moveTab(
  tabId: Long,
  screenId: Long,
  pos: Int,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """api/2/screens/${screenId}/tabs/${tabId}/move/${pos}""") {
    builder()
  }
}
