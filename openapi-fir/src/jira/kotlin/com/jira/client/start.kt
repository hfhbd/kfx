package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import kotlin.Unit

/**
 * Start exposing JMX metrics
 * Starts exposing JMX metrics
 */
public suspend fun HttpClient.start(builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/2/monitoring/jmx/startExposing""") {
    builder()
  }
}
