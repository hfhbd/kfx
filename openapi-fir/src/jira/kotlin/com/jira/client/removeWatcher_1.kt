package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Delete watcher from issue
 * Removes a user from an issue's watcher list.
 *
 * @param issueIdOrKey Issue id or key
 * @param userName The name of the user to remove from the watcher list.
 */
public suspend fun HttpClient.removeWatcher_1(
  issueIdOrKey: String,
  userName: String? = null,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/issue/${issueIdOrKey}/watchers""") {
    parameter("userName", userName)
    parameter("username", username)
    builder()
  }
}
