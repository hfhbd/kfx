package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import kotlin.String
import kotlin.Unit

/**
 * Merge versions
 * Merge versions
 */
public suspend fun HttpClient.merge(
  moveIssuesTo: String,
  id: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/version/${id}/mergeto/${moveIssuesTo}""") {
    builder()
  }
}
