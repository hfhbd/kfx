package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete a comment
 * Deletes an existing comment.
 *
 * @param issueIdOrKey Issue id or key
 * @param id Comment id
 */
public suspend fun HttpClient.deleteComment(
  issueIdOrKey: String,
  id: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/issue/${issueIdOrKey}/comment/${id}""") {
    builder()
  }
}
