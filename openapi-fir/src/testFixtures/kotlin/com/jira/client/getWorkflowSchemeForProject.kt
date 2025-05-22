package com.jira.client

import com.jira.WorkflowSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get workflow scheme for project
 * Returns the workflow scheme that is associated with requested project.
 */
public suspend fun HttpClient.getWorkflowSchemeForProject(projectKeyOrId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): WorkflowSchemeBean? {
  val response = `get`(urlString = """api/2/project/${projectKeyOrId}/workflowscheme""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<WorkflowSchemeBean>()
  return output
}
