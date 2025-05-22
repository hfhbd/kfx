package com.jira.client

import com.jira.CommentJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Update a comment
 * Updates an existing comment using its JSON representation.
 * @param issueIdOrKey Issue id or key
 * @param id Comment id
 * @param expand Optional flags: renderedBody (provides body rendered in HTML)
 */
public suspend fun HttpClient.updateComment(
  input: CommentJsonBean,
  issueIdOrKey: String,
  id: String,
  expand: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): CommentJsonBean {
  val response = put(urlString = """api/2/issue/${issueIdOrKey}/comment/${id}""") {
    parameter("expand", expand)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<CommentJsonBean>()
  return output
}
