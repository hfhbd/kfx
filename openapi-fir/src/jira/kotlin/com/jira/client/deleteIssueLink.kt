package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete an issue link with the specified id
 * Deletes an issue link with the specified id.
 *
 * @param linkId The issue link id.
 */
public suspend fun HttpClient.deleteIssueLink(linkId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """api/2/issueLink/${linkId}""") {
    builder()
  }
}
