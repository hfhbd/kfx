package com.jira.client

import com.jira.UserBean
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
 * Find users by username
 * Finds users.
 */
public suspend fun HttpClient.findUsers(
  includeInactive: Boolean? = null,
  maxResults: Int? = null,
  includeActive: Boolean? = null,
  startAt: Int? = null,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): UserBean? {
  val response = `get`(urlString = """api/2/user/search""") {
    parameter("includeInactive", includeInactive)
    parameter("maxResults", maxResults)
    parameter("includeActive", includeActive)
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
