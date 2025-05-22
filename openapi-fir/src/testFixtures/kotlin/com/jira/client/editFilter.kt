package com.jira.client

import com.jira.FilterBean
import com.jira.StringList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Update an existing filter
 * Updates an existing filter, and returns its new value. The following properties of a filter can be updated: 'jql', 'name', 'description'. Additionally, administrators can also update the 'owner' field. To get, set or unset 'favourite', use rest/api/1.0/filters/{id}/favourite with GET, PUT and DELETE methods instead.
 * @param id The filter id.
 */
public suspend fun HttpClient.editFilter(
  input: FilterBean,
  id: String,
  expand: StringList? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): FilterBean {
  val response = put(urlString = """api/2/filter/${id}""") {
    parameter("expand", expand)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<FilterBean>()
  return output
}
