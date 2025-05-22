package com.jira.client

import com.jira.WorkflowSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Long
import kotlin.Unit

/**
 * Get requested draft workflow scheme by ID
 * Returns the requested draft workflow scheme to the caller.
 */
public suspend fun HttpClient.getDraftById(id: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}): WorkflowSchemeBean? {
  val response = `get`(urlString = """api/2/workflowscheme/${id}/draft""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<WorkflowSchemeBean>()
  return output
}
