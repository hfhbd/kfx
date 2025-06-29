package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Set default columns for filter
 * Sets the default columns for the given filter
 *
 * @param id The filter id.
 */
public suspend fun HttpClient.setColumns_1(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = put(urlString = """api/2/filter/${id}/columns""") {
    contentType(ContentType.parse("*/*"))
    builder()
  }
}
