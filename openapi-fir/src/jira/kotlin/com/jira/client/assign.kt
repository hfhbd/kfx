package com.jira.client

import com.jira.UserBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Assign an issue to a user
 * Assign an issue to a user.
 * @param issueIdOrKey Issue id or key
 */
public suspend fun HttpClient.assign(
  input: UserBean,
  issueIdOrKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/issue/${issueIdOrKey}/assignee""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
