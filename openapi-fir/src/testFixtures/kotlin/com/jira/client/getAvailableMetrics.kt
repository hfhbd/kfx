package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get the available JMX metrics
 * Gets the available JMX metrics
 */
public suspend fun HttpClient.getAvailableMetrics(builder: suspend HttpRequestBuilder.() -> Unit = {}): String {
  val response = `get`(urlString = """api/2/monitoring/jmx/getAvailableMetrics""") {
    builder()
  }
  val output = response.body<String>()
  return output
}
