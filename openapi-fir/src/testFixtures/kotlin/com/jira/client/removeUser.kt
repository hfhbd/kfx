package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Delete user
 * Removes user and its references (like project roles associations, watches, history). Note: user references will not be removed if multiple User Directories are used and there is a user with the same name existing in another directory (shadowing user).
 */
public suspend fun HttpClient.removeUser(
  key: String? = null,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/user""") {
    parameter("key", key)
    parameter("username", username)
    builder()
  }
}
