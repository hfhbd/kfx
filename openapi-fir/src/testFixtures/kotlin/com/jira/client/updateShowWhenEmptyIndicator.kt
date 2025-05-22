package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Update 'showWhenEmptyIndicator' for a field
 * Update 'showWhenEmptyIndicator' for given field on screen.
 */
public suspend fun HttpClient.updateShowWhenEmptyIndicator(
  tabId: Long,
  screenId: Long,
  newValue: Boolean,
  id: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/screens/${screenId}/tabs/${tabId}/fields/${id}/updateShowWhenEmptyIndicator/${newValue}""") {
    builder()
  }
}
