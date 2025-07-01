package com.jira.client

import com.jira.VersionUnresolvedIssueCountsBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get version unresolved issues count
 * Returns the number of unresolved issues for the given version
 */
public suspend fun HttpClient.getVersionUnresolvedIssues(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): VersionUnresolvedIssueCountsBean? {
  val response = `get`(urlString = """api/2/version/${id}/unresolvedIssueCount""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<VersionUnresolvedIssueCountsBean>()
  return output
}
