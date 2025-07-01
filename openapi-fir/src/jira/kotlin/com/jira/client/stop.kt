package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import kotlin.Unit

/**
 * Stop exposing JMX metrics
 * Stops exposing JMX metrics
 */
public suspend fun HttpClient.stop(builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/2/monitoring/jmx/stopExposing""") {
    builder()
  }
}
