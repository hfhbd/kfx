package com.jira.client

import com.jira.IssueTypeMappingBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Get issue type mapping for a scheme
 * Returns the issue type mapping for the passed workflow scheme.
 */
public suspend fun HttpClient.getIssueType(
  issueType: String,
  id: Long,
  returnDraftIfExists: Boolean? = false,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): IssueTypeMappingBean? {
  val response = `get`(urlString = """api/2/workflowscheme/${id}/issuetype/${issueType}""") {
    parameter("returnDraftIfExists", returnDraftIfExists)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<IssueTypeMappingBean>()
  return output
}
