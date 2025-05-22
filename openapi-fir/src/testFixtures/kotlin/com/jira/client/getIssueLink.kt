package com.jira.client

import com.jira.IssueLinks
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get an issue link with the specified id
 * Returns an issue link with the specified id.
 *
 * @param linkId The issue link id.
 */
public suspend fun HttpClient.getIssueLink(linkId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): IssueLinks? {
  val response = `get`(urlString = """api/2/issueLink/${linkId}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<IssueLinks>()
  return output
}
