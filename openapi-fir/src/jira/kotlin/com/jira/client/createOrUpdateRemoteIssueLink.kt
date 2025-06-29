package com.jira.client

import com.jira.RemoteIssueLinkBean
import com.jira.RemoteIssueLinkCreateOrUpdateRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Create or update remote issue link
 * Creates or updates a remote issue link from a JSON representation. If a globalId is provided and a remote issue link exists with that globalId, the remote issue link is updated. Otherwise, the remote issue link is created.
 * @param issueIdOrKey Issue id or key
 */
public suspend fun HttpClient.createOrUpdateRemoteIssueLink(
  input: RemoteIssueLinkCreateOrUpdateRequest,
  issueIdOrKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): RemoteIssueLinkBean {
  val response = post(urlString = """api/2/issue/${issueIdOrKey}/remotelink""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<RemoteIssueLinkBean>()
  return output
}
