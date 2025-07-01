package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get user preference by key
 * Returns preference of the currently logged in user. Preference key must be provided as input parameter (key). The value is returned exactly as it is. If key parameter is not provided or wrong - status code 404. If value is found  - status code 200.
 */
public suspend fun HttpClient.getPreference(key: String? = null, builder: suspend HttpRequestBuilder.() -> Unit = {}): String? {
  val response = `get`(urlString = """api/2/mypreferences""") {
    parameter("key", key)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<String>()
  return output
}
