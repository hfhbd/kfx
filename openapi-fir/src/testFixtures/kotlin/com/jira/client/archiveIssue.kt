package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import kotlin.String
import kotlin.Unit

/**
 * Archive an issue
 * Archives an issue.
 *
 * @param issueIdOrKey Issue id or key
 * @param notifyUsers Send the email with notification that the issue was updated to users that watch it. Admin or project admin permissions are required to disable the notification.
 */
public suspend fun HttpClient.archiveIssue(
  issueIdOrKey: String,
  notifyUsers: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/issue/${issueIdOrKey}/archive""") {
    parameter("notifyUsers", notifyUsers)
    builder()
  }
}
