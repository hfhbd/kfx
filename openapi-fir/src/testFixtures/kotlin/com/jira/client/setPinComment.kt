package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Boolean
import kotlin.String
import kotlin.Unit

/**
 * Pin a comment
 * Pins a comment to the top of the comment list.
 * @param issueIdOrKey Issue id or key
 * @param id Comment id
 */
public suspend fun HttpClient.setPinComment(
  input: Boolean,
  issueIdOrKey: String,
  id: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/issue/${issueIdOrKey}/comment/${id}/pin""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
