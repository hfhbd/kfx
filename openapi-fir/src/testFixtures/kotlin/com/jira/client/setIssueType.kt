package com.jira.client

import com.jira.IssueTypeMappingBean
import com.jira.WorkflowSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Set an issue type mapping for a scheme
 * Set the issue type mapping for the passed scheme. The passed representation can have its updateDraftIfNeeded flag set to true to indicate that
 * the draft should be created/updated when the actual scheme cannot be edited.
 */
public suspend fun HttpClient.setIssueType(
  input: IssueTypeMappingBean,
  issueType: String,
  id: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): WorkflowSchemeBean? {
  val response = put(urlString = """api/2/workflowscheme/${id}/issuetype/${issueType}""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<WorkflowSchemeBean>()
  return output
}
