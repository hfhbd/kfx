package com.jira.client

import com.jira.WorkflowSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.contentType
import kotlin.Long
import kotlin.Unit

/**
 * Update a draft workflow scheme
 * Update a draft workflow scheme. The draft will created if necessary. The body of the request is a representation of the workflow scheme. Values not passed are assumed to indicate no change for that field.
 */
public suspend fun HttpClient.updateDraft(
  input: WorkflowSchemeBean,
  id: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): WorkflowSchemeBean? {
  val response = put(urlString = """api/2/workflowscheme/${id}/draft""") {
    contentType(Json)
    setBody(input)
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
