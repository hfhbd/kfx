package com.jira.client

import com.jira.NotificationJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Send notification to recipients
 * Sends a notification (email) to the list or recipients defined in the request.
 * @param issueIdOrKey Issue id or key
 */
public suspend fun HttpClient.notify(
  input: NotificationJsonBean,
  issueIdOrKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """api/2/issue/${issueIdOrKey}/notify""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
