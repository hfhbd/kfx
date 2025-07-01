package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete remote issue link by id
 * Delete the remote issue link with the given id on the issue.
 *
 * @param linkId Id of the remote issue link
 * @param issueIdOrKey Issue id or key
 */
public suspend fun HttpClient.deleteRemoteIssueLinkById(
  linkId: String,
  issueIdOrKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/issue/${issueIdOrKey}/remotelink/${linkId}""") {
    builder()
  }
}
