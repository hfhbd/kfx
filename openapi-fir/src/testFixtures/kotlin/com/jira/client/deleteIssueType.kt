package com.jira.client

import com.jira.WorkflowSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Delete an issue type mapping from a scheme
 * Remove the specified issue type mapping from the scheme.
 */
public suspend fun HttpClient.deleteIssueType(
  issueType: String,
  id: Long,
  updateDraftIfNeeded: Boolean? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): WorkflowSchemeBean? {
  val response = delete(urlString = """api/2/workflowscheme/${id}/issuetype/${issueType}""") {
    parameter("updateDraftIfNeeded", updateDraftIfNeeded)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<WorkflowSchemeBean>()
  return output
}
