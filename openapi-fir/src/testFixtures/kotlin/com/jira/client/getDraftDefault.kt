package com.jira.client

import com.jira.WorkflowSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Long
import kotlin.Unit

/**
 * Get default workflow for a draft scheme
 * Return the default workflow from the passed draft workflow scheme to the caller.
 */
public suspend fun HttpClient.getDraftDefault(id: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}): WorkflowSchemeBean? {
  val response = `get`(urlString = """api/2/workflowscheme/${id}/draft/default""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<WorkflowSchemeBean>()
  return output
}
