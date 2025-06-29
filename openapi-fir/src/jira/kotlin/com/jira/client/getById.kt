package com.jira.client

import com.jira.WorkflowSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Boolean
import kotlin.Long
import kotlin.Unit

/**
 * Get requested workflow scheme by ID
 * Returns the requested workflow scheme to the caller.
 */
public suspend fun HttpClient.getById(
  id: Long,
  returnDraftIfExists: Boolean? = false,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): WorkflowSchemeBean? {
  val response = `get`(urlString = """api/2/workflowscheme/${id}""") {
    parameter("returnDraftIfExists", returnDraftIfExists)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<WorkflowSchemeBean>()
  return output
}
