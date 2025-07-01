package com.jira.client

import com.jira.StatusJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get status by ID or name
 * Returns a full representation of the Status having the given id or name.
 */
public suspend fun HttpClient.getStatus(idOrName: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): StatusJsonBean? {
  val response = `get`(urlString = """api/2/status/${idOrName}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<StatusJsonBean>()
  return output
}
