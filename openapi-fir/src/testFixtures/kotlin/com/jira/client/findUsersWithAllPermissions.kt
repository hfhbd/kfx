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
 * Find users with all specified permissions
 * Returns a list of active users that match the search string and have all specified permissions for the project or issue. This resource can be accessed by users with ADMINISTER_PROJECT permission for the project or global ADMIN or SYSADMIN rights. This endpoint can cause serious performance issues and will be removed in Jira 9.0.
 */
public suspend fun HttpClient.findUsersWithAllPermissions(
  projectKey: String? = null,
  issueKey: String? = null,
  maxResults: Int? = null,
  permissions: String? = null,
  startAt: Int? = null,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): UserBean? {
  val response = `get`(urlString = """api/2/user/permission/search""") {
    parameter("projectKey", projectKey)
    parameter("issueKey", issueKey)
    parameter("maxResults", maxResults)
    parameter("permissions", permissions)
    parameter("startAt", startAt)
    parameter("username", username)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<UserBean>()
  return output
}
