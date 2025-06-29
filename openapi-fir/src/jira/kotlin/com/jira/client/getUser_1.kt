package com.jira.client

import com.jira.UserBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Boolean
import kotlin.String
import kotlin.Unit

/**
 * Get user by username or key
 * Returns a user.
 */
public suspend fun HttpClient.getUser_1(
  includeDeleted: Boolean? = false,
  key: String? = null,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): UserBean? {
  val response = `get`(urlString = """api/2/user""") {
    parameter("includeDeleted", includeDeleted)
    parameter("key", key)
    parameter("username", username)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<UserBean>()
  return output
}
