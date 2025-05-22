package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete a filter
 * Delete a filter
 *
 * @param id The ID of the filter to delete.
 */
public suspend fun HttpClient.deleteFilter(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """api/2/filter/${id}""") {
    builder()
  }
}
