package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Remove field from tab
 * Removes field from given tab.
 */
public suspend fun HttpClient.removeField(
  tabId: Long,
  screenId: Long,
  id: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/screens/${screenId}/tabs/${tabId}/fields/${id}""") {
    builder()
  }
}
