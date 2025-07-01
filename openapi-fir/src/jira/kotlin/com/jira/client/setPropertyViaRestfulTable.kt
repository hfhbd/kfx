package com.jira.client

import com.jira.Property
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import kotlin.String
import kotlin.Unit

/**
 * Update an application property
 * Update an application property via PUT. The "value" field present in the PUT will override the existing value.
 *
 * @param id a String containing the property key.
 */
public suspend fun HttpClient.setPropertyViaRestfulTable(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): Property? {
  val response = put(urlString = """api/2/application-properties/${id}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<Property>()
  return output
}
