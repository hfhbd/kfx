package com.jira.client

import com.jira.WorkflowSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Delete an issue type mapping from a draft scheme
 * Remove the specified issue type mapping from the draft scheme.
 */
public suspend fun HttpClient.deleteDraftIssueType(
  issueType: String,
  id: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): WorkflowSchemeBean? {
  val response = delete(urlString = """api/2/workflowscheme/${id}/draft/issuetype/${issueType}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<WorkflowSchemeBean>()
  return output
}
