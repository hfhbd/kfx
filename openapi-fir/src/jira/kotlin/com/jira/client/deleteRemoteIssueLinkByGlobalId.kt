package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Delete remote issue link
 * Delete the remote issue link with the given global id on the issue.
 *
 * @param issueIdOrKey Issue id or key
 * @param globalId Global id of the remote issue link
 */
public suspend fun HttpClient.deleteRemoteIssueLinkByGlobalId(
  issueIdOrKey: String,
  globalId: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/issue/${issueIdOrKey}/remotelink""") {
    parameter("globalId", globalId)
    builder()
  }
}
