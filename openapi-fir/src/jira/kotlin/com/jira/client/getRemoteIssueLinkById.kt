package com.jira.client

import com.jira.RemoteIssueLinkBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get a remote issue link by its id
 * Get a remote issue link by its id.
 *
 * @param linkId Id of the remote issue link
 * @param issueIdOrKey Issue id or key
 */
public suspend fun HttpClient.getRemoteIssueLinkById(
  linkId: String,
  issueIdOrKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): RemoteIssueLinkBean? {
  val response = `get`(urlString = """api/2/issue/${issueIdOrKey}/remotelink/${linkId}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<RemoteIssueLinkBean>()
  return output
}
