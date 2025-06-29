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
 * Find assignable users by username
 * Returns a list of users that match the search string. This resource cannot be accessed anonymously. Please note that this resource should be called with an issue key when a list of assignable users is retrieved. For create only a project key should be supplied. The list of assignable users may be incorrect if it's called with the project key for editing.
 */
public suspend fun HttpClient.findAssignableUsers_1(
  issueKey: String? = null,
  maxResults: Int? = 50,
  project: String? = null,
  actionDescriptorId: Int? = null,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): UserBean? {
  val response = `get`(urlString = """api/2/user/assignable/search""") {
    parameter("issueKey", issueKey)
    parameter("maxResults", maxResults)
    parameter("project", project)
    parameter("actionDescriptorId", actionDescriptorId)
    parameter("username", username)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<UserBean>()
  return output
}
