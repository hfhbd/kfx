package com.jira.client

import com.jira.PriorityJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get all issue priorities
 * Returns a list of all issue priorities
 */
public suspend fun HttpClient.getPriorities(builder: suspend HttpRequestBuilder.() -> Unit = {}): PriorityJsonBean {
  val response = `get`(urlString = """api/2/priority""") {
    builder()
  }
  val output = response.body<PriorityJsonBean>()
  return output
}
