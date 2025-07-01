package com.jira.client

import com.jira.PrioritySchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get assigned priority scheme
 * Gets a full representation of a priority scheme in JSON format used by specified project. User must be global administrator or project administrator. All project keys associated with the priority scheme will only be returned if additional query parameter is provided expand=projectKeys.
 */
public suspend fun HttpClient.getAssignedPriorityScheme(projectKeyOrId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): PrioritySchemeBean? {
  val response = `get`(urlString = """api/2/project/${projectKeyOrId}/priorityscheme""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<PrioritySchemeBean>()
  return output
}
