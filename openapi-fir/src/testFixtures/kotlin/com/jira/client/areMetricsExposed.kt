package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Boolean
import kotlin.Unit

/**
 * Check if JMX metrics are being exposed
 * Checks if JMX metrics are being exposed
 */
public suspend fun HttpClient.areMetricsExposed(builder: suspend HttpRequestBuilder.() -> Unit = {}): Boolean {
  val response = `get`(urlString = """api/2/monitoring/jmx/areMetricsExposed""") {
    builder()
  }
  val output = response.body<Boolean>()
  return output
}
