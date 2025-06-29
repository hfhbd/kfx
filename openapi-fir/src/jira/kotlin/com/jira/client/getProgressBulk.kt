package com.jira.client

import com.jira.ReindexRequestBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Long
import kotlin.Unit
import kotlin.collections.List

/**
 * Get progress of multiple reindex requests
 * Retrieves the progress of multiple reindex requests. Only reindex requests that actually exist will be returned in the results.
 */
public suspend fun HttpClient.getProgressBulk(requestId: List<Long>? = null, builder: suspend HttpRequestBuilder.() -> Unit = {}): ReindexRequestBean {
  val response = `get`(urlString = """api/2/reindex/request/bulk""") {
    parameter("requestId", requestId)
    builder()
  }
  val output = response.body<ReindexRequestBean>()
  return output
}
