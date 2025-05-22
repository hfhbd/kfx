package com.jira.client

import com.jira.ResolutionBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Get paginated filtered resolutions
 * Returns paginated list of filtered resolutions.
 */
public suspend fun HttpClient.getPaginatedResolutions(
  maxResults: Int? = 100,
  query: String? = "",
  startAt: Long? = 0,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ResolutionBean {
  val response = `get`(urlString = """api/2/resolution/page""") {
    parameter("maxResults", maxResults)
    parameter("query", query)
    parameter("startAt", startAt)
    builder()
  }
  val output = response.body<ResolutionBean>()
  return output
}
