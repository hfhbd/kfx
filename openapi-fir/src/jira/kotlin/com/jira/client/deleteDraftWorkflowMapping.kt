package com.jira.client

import com.jira.WorkflowSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode.Companion.NotFound
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Delete a workflow mapping from a draft scheme
 * Delete the passed workflow from the draft workflow scheme.
 */
public suspend fun HttpClient.deleteDraftWorkflowMapping(
  id: Long,
  workflowName: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): WorkflowSchemeBean? {
  val response = delete(urlString = """api/2/workflowscheme/${id}/draft/workflow""") {
    parameter("workflowName", workflowName)
    builder()
  }
  when {
    response.status == NotFound -> {
      return null
    }
  }
  val output = response.body<WorkflowSchemeBean>()
  return output
}
