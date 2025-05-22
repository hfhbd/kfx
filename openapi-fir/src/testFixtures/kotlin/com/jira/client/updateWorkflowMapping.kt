package com.jira.client

import com.jira.WorkflowMappingBean
import com.jira.WorkflowSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Update a workflow mapping in a scheme
 * Update the scheme to include the passed mapping. The body is a representation of the workflow mapping. Values not passed are assumed to indicate no change for that field.
 * The passed representation can have its updateDraftIfNeeded flag set to true to indicate that the draft
 * should be created/updated when the actual scheme cannot be edited.
 */
public suspend fun HttpClient.updateWorkflowMapping(
  input: WorkflowMappingBean,
  id: Long,
  workflowName: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): WorkflowSchemeBean {
  val response = put(urlString = """api/2/workflowscheme/${id}/workflow""") {
    parameter("workflowName", workflowName)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<WorkflowSchemeBean>()
  return output
}
