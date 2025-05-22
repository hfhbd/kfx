package com.jira.client

import com.jira.WorkflowSchemeBean
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
 * Get workflow mappings for a scheme
 * Returns the workflow mappings or requested mapping to the caller for the passed scheme.
 */
public suspend fun HttpClient.getWorkflow(
  id: Long,
  workflowName: String? = null,
  returnDraftIfExists: Boolean? = false,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): WorkflowSchemeBean? {
  val response = `get`(urlString = """api/2/workflowscheme/${id}/workflow""") {
    parameter("workflowName", workflowName)
    parameter("returnDraftIfExists", returnDraftIfExists)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<WorkflowSchemeBean>()
  return output
}
