package com.jira.client

import com.jira.PinnedCommentJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get pinned comments for an issue
 * Returns all pinned to the issue comments.
 *
 * @param issueIdOrKey Issue id or key
 */
public suspend fun HttpClient.getPinnedComments(issueIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): PinnedCommentJsonBean? {
  val response = `get`(urlString = """api/2/issue/${issueIdOrKey}/pinned-comments""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<PinnedCommentJsonBean>()
  return output
}
