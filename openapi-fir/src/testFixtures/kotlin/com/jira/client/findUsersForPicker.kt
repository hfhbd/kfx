package com.jira.client

import com.jira.UserPickerResultsBean
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
 * Find users for picker by query
 * Returns a list of users matching query with highlighting.
 */
public suspend fun HttpClient.findUsersForPicker(
  maxResults: Int? = null,
  query: String? = null,
  exclude: List<String>? = null,
  showAvatar: Boolean? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): UserPickerResultsBean? {
  val response = `get`(urlString = """api/2/user/picker""") {
    parameter("maxResults", maxResults)
    parameter("query", query)
    parameter("exclude", exclude)
    parameter("showAvatar", showAvatar)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<UserPickerResultsBean>()
  return output
}
