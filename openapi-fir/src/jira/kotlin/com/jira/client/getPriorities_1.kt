package com.jira.client

import com.jira.PriorityJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.collections.List

/**
 * Get paginated issue priorities
 * Returns a page with list of issue priorities whose names (or their translations) match query
 */
public suspend fun HttpClient.getPriorities_1(
  maxResults: Int? = 100,
  query: String? = "",
  projectIds: List<Long>? = null,
  startAt: Long? = 0,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): PriorityJsonBean {
  val response = `get`(urlString = """api/2/priority/page""") {
    parameter("maxResults", maxResults)
    parameter("query", query)
    parameter("projectIds", projectIds)
    parameter("startAt", startAt)
    builder()
  }
  val output = response.body<PriorityJsonBean>()
  return output
}
