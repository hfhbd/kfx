package com.jira.client

import com.jira.RemoteIssueLinkBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get remote issue links for an issue
 * Get remote issue links for an issue.
 *
 * @param issueIdOrKey Issue id or key
 * @param globalId Global id of the remote issue link
 */
public suspend fun HttpClient.getRemoteIssueLinks(
  issueIdOrKey: String,
  globalId: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): RemoteIssueLinkBean? {
  val response = `get`(urlString = """api/2/issue/${issueIdOrKey}/remotelink""") {
    parameter("globalId", globalId)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<RemoteIssueLinkBean>()
  return output
}
