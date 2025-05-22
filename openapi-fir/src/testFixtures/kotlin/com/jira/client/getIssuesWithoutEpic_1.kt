package com.jira.client

import com.jira.SearchResultsBean
import com.jira.StringList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.collections.List

/**
 * Get issues without an epic
 * Returns all issues that do not belong to any epic. This only includes issues that the user has permission to view. Issues returned from this resource include Agile fields, like sprint, closedSprints, flagged, and epic. By default, the returned issues are ordered by rank.
 */
public suspend fun HttpClient.getIssuesWithoutEpic_1(
  expand: String? = null,
  jql: String? = null,
  maxResults: Int? = null,
  validateQuery: Boolean? = null,
  fields: List<StringList>? = null,
  startAt: Long? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): SearchResultsBean {
  val response = `get`(urlString = """agile/1.0/epic/none/issue""") {
    parameter("expand", expand)
    parameter("jql", jql)
    parameter("maxResults", maxResults)
    parameter("validateQuery", validateQuery)
    parameter("fields", fields)
    parameter("startAt", startAt)
    builder()
  }
  val output = response.body<SearchResultsBean>()
  return output
}
