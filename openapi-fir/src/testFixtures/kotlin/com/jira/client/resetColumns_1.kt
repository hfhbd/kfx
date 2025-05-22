package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Reset columns for filter
 * Resets the columns for the given filter such that the filter no longer has its own column config
 *
 * @param id The filter id.
 */
public suspend fun HttpClient.resetColumns_1(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """api/2/filter/${id}/columns""") {
    builder()
  }
}
