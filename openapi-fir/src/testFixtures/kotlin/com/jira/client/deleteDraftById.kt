package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.Long
import kotlin.Unit

/**
 * Delete the specified draft workflow scheme
 * Delete the passed draft workflow scheme.
 */
public suspend fun HttpClient.deleteDraftById(id: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """api/2/workflowscheme/${id}/draft""") {
    builder()
  }
}
