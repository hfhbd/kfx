package com.jira.client

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
 * Update a specified workflow scheme
 * Update the passed workflow scheme. The body of the request is a representation of the workflow scheme. Values not passed are assumed to indicate no change for that field.
 * The passed representation can have its updateDraftIfNeeded flag set to true to indicate that the draft
 * should be created and/or updated when the actual scheme cannot be edited (e.g. when the scheme is being used by
 * a project). Values not appearing the body will not be touched.
 */
public suspend fun HttpClient.update(
  input: WorkflowSchemeBean,
  id: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): WorkflowSchemeBean? {
  val response = put(urlString = """api/2/workflowscheme/${id}""") {
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
