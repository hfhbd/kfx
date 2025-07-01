package com.jira.client

import com.jira.WorklogChangedSinceBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Long
import kotlin.Unit

/**
 * Returns worklogs deleted since given time.
 * Returns worklogs id and delete time of worklogs that was deleted since given time. The returns set of worklogs is limited to 1000 elements. This API will not return worklogs deleted during last minute.
 */
public suspend fun HttpClient.getIdsOfWorklogsDeletedSince(since: Long? = 0, builder: suspend HttpRequestBuilder.() -> Unit = {}): WorklogChangedSinceBean {
  val response = `get`(urlString = """api/2/worklog/deleted""") {
    parameter("since", since)
    builder()
  }
  val output = response.body<WorklogChangedSinceBean>()
  return output
}
