package com.jira.client

import com.jira.PrioritySchemeListBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Int
import kotlin.Long
import kotlin.Unit

/**
 * Get all priority schemes
 * Returns all priority schemes. All project keys associated with the priority scheme will only be returned if additional query parameter is provided <code>expand=schemes.projectKeys</code>
 */
public suspend fun HttpClient.getPrioritySchemes(
  maxResults: Int? = null,
  startAt: Long? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): PrioritySchemeListBean {
  val response = `get`(urlString = """api/2/priorityschemes""") {
    parameter("maxResults", maxResults)
    parameter("startAt", startAt)
    builder()
  }
  val output = response.body<PrioritySchemeListBean>()
  return output
}
