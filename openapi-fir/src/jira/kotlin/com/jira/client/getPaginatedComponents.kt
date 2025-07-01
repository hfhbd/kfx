package com.jira.client

import com.jira.PageBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get paginated components
 * Returns paginated list of filtered active components
 *
 * @param maxResults the maximum number of components to return
 * @param query the string that components names will be matched with
 * @param projectIds the set of project ids to filter components
 * @param startAt the index of the first components to return
 */
public suspend fun HttpClient.getPaginatedComponents(
  maxResults: String? = "100",
  query: String? = null,
  projectIds: String? = null,
  startAt: String? = "0",
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): PageBean {
  val response = `get`(urlString = """api/2/component/page""") {
    parameter("maxResults", maxResults)
    parameter("query", query)
    parameter("projectIds", projectIds)
    parameter("startAt", startAt)
    builder()
  }
  val output = response.body<PageBean>()
  return output
}
