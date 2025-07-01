package com.jira.client

import com.jira.CommentJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Add a comment
 * Adds a new comment to an issue.
 * @param issueIdOrKey Issue id or key
 * @param expand Optional flags: renderedBody (provides body rendered in HTML)
 */
public suspend fun HttpClient.addComment(
  input: CommentJsonBean,
  issueIdOrKey: String,
  expand: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): CommentJsonBean {
  val response = post(urlString = """api/2/issue/${issueIdOrKey}/comment""") {
    parameter("expand", expand)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<CommentJsonBean>()
  return output
}
