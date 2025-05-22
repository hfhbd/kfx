package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Text.Plain
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Archive list of issues
 * Archives a list of issues.
 * @param notifyUsers Send the email with notification that the issue was updated to users that watch it. Admin or project admin permissions are required to disable the notification.
 */
public suspend fun HttpClient.archiveIssues(
  input: String,
  notifyUsers: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): String? {
  val response = post(urlString = """api/2/issue/archive""") {
    parameter("notifyUsers", notifyUsers)
    contentType(Plain)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<String>()
  return output
}
