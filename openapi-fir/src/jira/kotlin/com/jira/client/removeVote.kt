package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Remove vote from issue
 * Remove your vote from an issue.
 *
 * @param issueIdOrKey Issue id or key
 */
public suspend fun HttpClient.removeVote(issueIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """api/2/issue/${issueIdOrKey}/votes""") {
    builder()
  }
}
