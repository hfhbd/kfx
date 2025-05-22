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
 * Find users with browse permission
 * Returns a list of active users that match the search string. This resource cannot be accessed anonymously and requires the Browse Users global permission. Given an issue key this resource will provide a list of users that match the search string and have the browse issue permission for the issue provided.
 */
public suspend fun HttpClient.findUsersWithBrowsePermission(
  projectKey: String? = null,
  issueKey: String? = null,
  maxResults: Int? = null,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): UserBean? {
  val response = `get`(urlString = """api/2/user/viewissue/search""") {
    parameter("projectKey", projectKey)
    parameter("issueKey", issueKey)
    parameter("maxResults", maxResults)
    parameter("username", username)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<UserBean>()
  return output
}
