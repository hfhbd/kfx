package com.jira.client

import com.jira.VersionBean
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
 * Get paginated versions
 * Retrieve paginated collection of versions matching given query optionally filtered by given project IDs.
 */
public suspend fun HttpClient.getPaginatedVersions(
  maxResults: Int? = 100,
  query: String? = "",
  projectIds: List<Long>? = null,
  startAt: Long? = 0,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): VersionBean {
  val response = `get`(urlString = """api/2/version""") {
    parameter("maxResults", maxResults)
    parameter("query", query)
    parameter("projectIds", projectIds)
    parameter("startAt", startAt)
    builder()
  }
  val output = response.body<VersionBean>()
  return output
}
