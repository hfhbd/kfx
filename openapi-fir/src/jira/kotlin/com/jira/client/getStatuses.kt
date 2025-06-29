package com.jira.client

import com.jira.StatusJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get all statuses
 * Returns a list of all statuses
 */
public suspend fun HttpClient.getStatuses(builder: suspend HttpRequestBuilder.() -> Unit = {}): StatusJsonBean? {
  val response = `get`(urlString = """api/2/status""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<StatusJsonBean>()
  return output
}
