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
import kotlin.String
import kotlin.Unit
import kotlin.collections.List

/**
 * Get issues using JQL
 * Searches for issues using JQL.
 * Sorting
 * the jql parameter is a full <a href="http://confluence.atlassian.com/display/JIRA/Advanced+Searching">JQL</a>
 * expression, and includes an ORDER BY clause.
 * The fields param (which can be specified multiple times) gives a comma-separated list of fields
 * to include in the response. This can be used to retrieve a subset of fields.
 * A particular field can be excluded by prefixing it with a minus.
 * By default, only navigable (*navigable) fields are returned in this search resource. Note: the default is different
 * in the get-issue resource -- the default there all fields (*all).
 * *all - include all fields
 * *navigable - include just navigable fields
 * summary,comment - include just the summary and comments
 * -description - include navigable fields except the description (the default is *navigable for search)
 * *all,-comment - include everything except comments
 * GET vs POST:
 * If the JQL query is too large to be encoded as a query param you should instead
 * POST to this resource.
 * Expanding Issues in the Search Result:
 * It is possible to expand the issues returned by directly specifying the expansion on the expand parameter passed
 * in to this resources.
 * For instance, to expand the changelog for all the issues on the search result, it is necessary to
 * specify changelog as one of the values to expand.
 */
public suspend fun HttpClient.search_1(
  expand: StringList? = null,
  jql: String? = null,
  maxResults: Int? = null,
  validateQuery: Boolean? = true,
  fields: List<StringList>? = null,
  startAt: Int? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): SearchResultsBean {
  val response = `get`(urlString = """api/2/search""") {
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
