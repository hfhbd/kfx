package com.jira.client

import com.jira.WorkflowSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.Boolean
import kotlin.Long
import kotlin.Unit

/**
 * Remove default workflow from a scheme
 * Remove the default workflow from the passed workflow scheme.
 */
public suspend fun HttpClient.deleteDefault(
  id: Long,
  updateDraftIfNeeded: Boolean? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): WorkflowSchemeBean? {
  val response = delete(urlString = """api/2/workflowscheme/${id}/default""") {
    parameter("updateDraftIfNeeded", updateDraftIfNeeded)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<WorkflowSchemeBean>()
  return output
}
