package com.jira.client

import com.jira.RemoteIssueLinkCreateOrUpdateRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Update remote issue link
 * Updates a remote issue link from a JSON representation. Any fields not provided are set to null.
 * @param linkId Id of the remote issue link
 * @param issueIdOrKey Issue id or key
 */
public suspend fun HttpClient.updateRemoteIssueLink(
  input: RemoteIssueLinkCreateOrUpdateRequest,
  linkId: String,
  issueIdOrKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/issue/${issueIdOrKey}/remotelink/${linkId}""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
