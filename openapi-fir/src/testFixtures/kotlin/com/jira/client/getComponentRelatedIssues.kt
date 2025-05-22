package com.jira.client

import com.jira.ComponentIssueCountsBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get component related issues
 * Returns counts of issues related to this component.
 *
 * @param id a String containing the component id
 */
public suspend fun HttpClient.getComponentRelatedIssues(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): ComponentIssueCountsBean? {
  val response = `get`(urlString = """api/2/component/${id}/relatedIssueCounts""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ComponentIssueCountsBean>()
  return output
}
