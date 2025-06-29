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
 * Delete a workflow mapping from a scheme
 * Delete the passed workflow from the workflow scheme.
 */
public suspend fun HttpClient.deleteWorkflowMapping(
  id: Long,
  updateDraftIfNeeded: Boolean? = null,
  workflowName: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): WorkflowSchemeBean? {
  val response = delete(urlString = """api/2/workflowscheme/${id}/workflow""") {
    parameter("updateDraftIfNeeded", updateDraftIfNeeded)
    parameter("workflowName", workflowName)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<WorkflowSchemeBean>()
  return output
}
