package com.jira.client

import com.jira.ErrorCollection
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get project key validation
 * Validates a project key.
 */
public suspend fun HttpClient.getProject_1(key: String? = null, builder: suspend HttpRequestBuilder.() -> Unit = {}): ErrorCollection {
  val response = `get`(urlString = """api/2/projectvalidate/key""") {
    parameter("key", key)
    builder()
  }
  val output = response.body<ErrorCollection>()
  return output
}
