package com.jira.client

import com.jira.ProjectPickerResultWrapper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Unit

/**
 * Get projects matching query
 * Returns a list of projects visible to the user where project name and/or key is matching the given query.
 * Passing an empty (or whitespace only) query will match no projects. The project matches will
 * contain a field with the query highlighted.
 * The number of projects returned can be controlled by passing a value for 'maxResults', but a hard limit of no
 * more than 100 projects is enforced. The projects are wrapped in a single response object that contains
 * a header for use in the picker, specifically 'Showing X of Y matching projects' and the total number
 * of matches for the query.
 */
public suspend fun HttpClient.searchForProjects(
  maxResults: Int? = 0,
  query: String? = "",
  allowEmptyQuery: Boolean? = false,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ProjectPickerResultWrapper {
  val response = `get`(urlString = """api/2/projects/picker""") {
    parameter("maxResults", maxResults)
    parameter("query", query)
    parameter("allowEmptyQuery", allowEmptyQuery)
    builder()
  }
  val output = response.body<ProjectPickerResultWrapper>()
  return output
}
