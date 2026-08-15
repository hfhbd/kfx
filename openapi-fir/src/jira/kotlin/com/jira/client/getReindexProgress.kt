package com.jira.client

import com.jira.ReindexBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode.Companion.NotFound
import kotlin.Long
import kotlin.Unit

/**
 * Get reindex progress
 * Returns information on the system reindexes. If a reindex is currently taking place then information about this reindex is returned. If there is no active index task, then returns information about the latest reindex task run, otherwise returns a 404 indicating that no reindex has taken place.
 */
public suspend fun HttpClient.getReindexProgress(taskId: Long? = null, builder: suspend HttpRequestBuilder.() -> Unit = {}): ReindexBean? {
  val response = `get`(urlString = """api/2/reindex/progress""") {
    parameter("taskId", taskId)
    builder()
  }
  if (response.status == NotFound) {
    return null
  }
  val output = response.body<ReindexBean>()
  return output
}
