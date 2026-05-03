package com.jira.client

import com.jira.WorkflowSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.http.HttpStatusCode.Companion.NotFound
import kotlin.Long
import kotlin.Unit

/**
 * Remove default workflow from a draft scheme
 * Remove the default workflow from the passed draft workflow scheme.
 */
public suspend fun HttpClient.deleteDraftDefault(id: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}): WorkflowSchemeBean? {
  val response = delete(urlString = """api/2/workflowscheme/${id}/draft/default""") {
    builder()
  }
  if (response.status == NotFound) {
    return null
  }
  val output = response.body<WorkflowSchemeBean>()
  return output
}
