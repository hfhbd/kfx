package com.jira.client

import com.jira.WatchersBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get list of watchers of issue
 * Returns the list of watchers for the issue with the given key.
 *
 * @param issueIdOrKey Issue id or key
 */
public suspend fun HttpClient.getIssueWatchers(issueIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): WatchersBean? {
  val response = `get`(urlString = """api/2/issue/${issueIdOrKey}/watchers""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<WatchersBean>()
  return output
}
