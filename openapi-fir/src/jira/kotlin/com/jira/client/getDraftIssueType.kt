package com.jira.client

import com.jira.IssueTypeMappingBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Get issue type mapping for a draft scheme
 * Returns the issue type mapping for the passed draft workflow scheme.
 */
public suspend fun HttpClient.getDraftIssueType(
  issueType: String,
  id: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): IssueTypeMappingBean? {
  val response = `get`(urlString = """api/2/workflowscheme/${id}/draft/issuetype/${issueType}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<IssueTypeMappingBean>()
  return output
}
