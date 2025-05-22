package com.jira.client

import com.jira.ColumnOptions
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get default columns for user
 * Returns the default columns for the given user. Admin permission will be required to get columns for a user other than the currently logged in user.
 */
public suspend fun HttpClient.defaultColumns(username: String? = null, builder: suspend HttpRequestBuilder.() -> Unit = {}): ColumnOptions? {
  val response = `get`(urlString = """api/2/user/columns""") {
    parameter("username", username)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ColumnOptions>()
  return output
}
