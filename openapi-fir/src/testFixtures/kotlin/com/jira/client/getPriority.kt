package com.jira.client

import com.jira.PriorityJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get an issue priority by ID
 * Returns an issue priority
 */
public suspend fun HttpClient.getPriority(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): PriorityJsonBean? {
  val response = `get`(urlString = """api/2/priority/${id}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<PriorityJsonBean>()
  return output
}
