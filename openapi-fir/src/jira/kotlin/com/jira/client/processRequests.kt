package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import kotlin.Long
import kotlin.Unit

/**
 * Execute pending reindex requests
 * Executes any pending reindex requests. Execution is asynchronous - progress of the returned tasks can be monitored through other REST calls.
 */
public suspend fun HttpClient.processRequests(builder: suspend HttpRequestBuilder.() -> Unit = {}): Long {
  val response = post(urlString = """api/2/reindex/request""") {
    builder()
  }
  val output = response.body<Long>()
  return output
}
