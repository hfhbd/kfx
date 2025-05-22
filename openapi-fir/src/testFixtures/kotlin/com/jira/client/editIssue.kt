package com.jira.client

import com.jira.IssueUpdateBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Edit an issue from a JSON representation
 * Edits an issue from a JSON representation. The issue can either be updated by setting explicit the field value(s) or by using an operation to change the field value.
 * @param issueIdOrKey Issue id or key
 * @param notifyUsers Send the email with notification that the issue was updated to users that watch it. Admin or project admin permissions are required to disable the notification.
 */
public suspend fun HttpClient.editIssue(
  input: IssueUpdateBean,
  issueIdOrKey: String,
  notifyUsers: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/issue/${issueIdOrKey}""") {
    parameter("notifyUsers", notifyUsers)
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
