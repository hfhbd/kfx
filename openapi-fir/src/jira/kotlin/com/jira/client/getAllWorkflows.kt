package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get all workflows
 * Returns all workflows. The âlastModifiedDateâ is returned in Jira Complete Date/Time Format (dd/MMM/yy h:mm by default), but can also be returned as a relative date.
 *
 * @param workflowName an optional String containing workflow name. If not passed then all workflows are returned
 */
public suspend fun HttpClient.getAllWorkflows(workflowName: String? = null, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = `get`(urlString = """api/2/workflow""") {
    parameter("workflowName", workflowName)
    builder()
  }
}
