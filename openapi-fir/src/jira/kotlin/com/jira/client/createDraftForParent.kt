package com.jira.client

import com.jira.WorkflowSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import kotlin.Long
import kotlin.Unit

/**
 * Create a draft for a workflow scheme
 * Create a draft for the passed scheme. The draft will be a copy of the state of the parent.
 */
public suspend fun HttpClient.createDraftForParent(id: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}): WorkflowSchemeBean? {
  val response = post(urlString = """api/2/workflowscheme/${id}/createdraft""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<WorkflowSchemeBean>()
  return output
}
