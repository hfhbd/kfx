package com.jira.client

import com.jira.GroupSuggestionsBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get groups matching a query
 * Returns groups with substrings matching a given query
 *
 * @param maxResults Maximum number of results to return
 * @param query A String to match groups against
 * @param exclude List of groups to exclude
 * @param userName Username for the context
 */
public suspend fun HttpClient.findGroups(
  maxResults: String? = null,
  query: String? = null,
  exclude: String? = null,
  userName: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): GroupSuggestionsBean {
  val response = `get`(urlString = """api/2/groups/picker""") {
    parameter("maxResults", maxResults)
    parameter("query", query)
    parameter("exclude", exclude)
    parameter("userName", userName)
    builder()
  }
  val output = response.body<GroupSuggestionsBean>()
  return output
}
