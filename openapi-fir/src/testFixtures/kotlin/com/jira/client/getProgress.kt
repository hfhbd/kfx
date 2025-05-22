package com.jira.client

import com.jira.ReindexRequestBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Long
import kotlin.Unit

/**
 * Get progress of a single reindex request
 * Retrieves the progress of a single reindex request.
 */
public suspend fun HttpClient.getProgress(requestId: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}): ReindexRequestBean? {
  val response = `get`(urlString = """api/2/reindex/request/${requestId}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ReindexRequestBean>()
  return output
}
