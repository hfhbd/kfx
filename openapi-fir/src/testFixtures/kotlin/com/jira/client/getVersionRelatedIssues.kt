package com.jira.client

import com.jira.VersionIssueCountsBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get version related issues count
 * Returns a bean containing the number of fixed in and affected issues for the given version.
 */
public suspend fun HttpClient.getVersionRelatedIssues(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): VersionIssueCountsBean? {
  val response = `get`(urlString = """api/2/version/${id}/relatedIssueCounts""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<VersionIssueCountsBean>()
  return output
}
