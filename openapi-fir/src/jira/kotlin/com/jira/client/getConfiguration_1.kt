package com.jira.client

import com.jira.ConfigurationBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get Jira configuration details
 * Returns the information if the optional features in Jira are enabled or disabled. If the time tracking is enabled, it also returns the detailed information about time tracking configuration.
 */
public suspend fun HttpClient.getConfiguration_1(builder: suspend HttpRequestBuilder.() -> Unit = {}): ConfigurationBean {
  val response = `get`(urlString = """api/2/configuration""") {
    builder()
  }
  val output = response.body<ConfigurationBean>()
  return output
}
