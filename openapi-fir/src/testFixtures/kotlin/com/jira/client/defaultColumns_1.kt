package com.jira.client

import com.jira.ColumnLayout
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get default columns for filter
 * Returns the default columns for the given filter. Currently logged in user will be used as the user making such request.
 *
 * @param id The filter id.
 */
public suspend fun HttpClient.defaultColumns_1(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): ColumnLayout? {
  val response = `get`(urlString = """api/2/filter/${id}/columns""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ColumnLayout>()
  return output
}
