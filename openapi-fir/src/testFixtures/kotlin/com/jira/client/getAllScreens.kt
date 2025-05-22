package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get available field screens
 * Adds field or custom field to the default tab.
 */
public suspend fun HttpClient.getAllScreens(
  search: String? = null,
  expand: String? = null,
  maxResults: String? = null,
  startAt: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = `get`(urlString = """api/2/screens""") {
    parameter("search", search)
    parameter("expand", expand)
    parameter("maxResults", maxResults)
    parameter("startAt", startAt)
    builder()
  }
}
