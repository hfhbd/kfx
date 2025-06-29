package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.Long
import kotlin.Unit

/**
 * Delete a tab from a screen
 * Deletes tab from given screen.
 */
public suspend fun HttpClient.deleteTab(
  tabId: Long,
  screenId: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/screens/${screenId}/tabs/${tabId}""") {
    builder()
  }
}
