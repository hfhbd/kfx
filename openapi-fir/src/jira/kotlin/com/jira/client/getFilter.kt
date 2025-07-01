package com.jira.client

import com.jira.FilterBean
import com.jira.StringList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get a filter by ID
 * Returns a filter given an id
 *
 * @param id The filter id.
 */
public suspend fun HttpClient.getFilter(
  id: String,
  expand: StringList? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): FilterBean {
  val response = `get`(urlString = """api/2/filter/${id}""") {
    parameter("expand", expand)
    builder()
  }
  val output = response.body<FilterBean>()
  return output
}
