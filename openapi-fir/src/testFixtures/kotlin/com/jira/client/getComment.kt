package com.jira.client

import com.jira.CommentJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get a comment by id
 * Returns a single comment.
 *
 * @param issueIdOrKey Issue id or key
 * @param id Comment id
 * @param expand Optional flags: renderedBody (provides body rendered in HTML)
 */
public suspend fun HttpClient.getComment(
  issueIdOrKey: String,
  id: String,
  expand: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): CommentJsonBean? {
  val response = `get`(urlString = """api/2/issue/${issueIdOrKey}/comment/${id}""") {
    parameter("expand", expand)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<CommentJsonBean>()
  return output
}
