package com.jira.client

import com.jira.DashboardsBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get all dashboards with optional filtering
 * Returns a list of all dashboards, optionally filtering them.
 *
 * @param filter An optional filter that is applied to the list of dashboards.
 * @param maxResults A hint as to the maximum number of dashboards to return in each call.
 * @param startAt The index of the first dashboard to return (0-based).
 */
public suspend fun HttpClient.list(
  filter: String? = null,
  maxResults: String? = null,
  startAt: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): DashboardsBean {
  val response = `get`(urlString = """api/2/dashboard""") {
    parameter("filter", filter)
    parameter("maxResults", maxResults)
    parameter("startAt", startAt)
    builder()
  }
  val output = response.body<DashboardsBean>()
  return output
}
