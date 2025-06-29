package com.jira.client

import com.jira.UserBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Int
import kotlin.String
import kotlin.Unit

/**
 * Find bulk assignable users
 * Returns a list of users that match the search string and can be assigned issues for all the given projects.
 */
public suspend fun HttpClient.findBulkAssignableUsers(
  maxResults: Int? = 50,
  projectKeys: String? = null,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): UserBean? {
  val response = `get`(urlString = """api/2/user/assignable/multiProjectSearch""") {
    parameter("maxResults", maxResults)
    parameter("projectKeys", projectKeys)
    parameter("username", username)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<UserBean>()
  return output
}
