package com.jira.client

import com.jira.DefaultBean
import com.jira.WorkflowSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Long
import kotlin.Unit

/**
 * Update default workflow for a scheme
 * Set the default workflow for the passed workflow scheme. The passed representation can have its
 * updateDraftIfNeeded flag set to true to indicate that the draft should be created/updated when the actual scheme
 * cannot be edited.
 */
public suspend fun HttpClient.updateDefault(
  input: DefaultBean,
  id: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): WorkflowSchemeBean? {
  val response = put(urlString = """api/2/workflowscheme/${id}/default""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<WorkflowSchemeBean>()
  return output
}
