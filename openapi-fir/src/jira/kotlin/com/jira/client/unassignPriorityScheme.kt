package com.jira.client

import com.jira.PrioritySchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Unassign project from priority scheme
 * Unassigns project from priority scheme. Operation will fail for defualt priority scheme, project is not found or project is not associated with provided priority scheme. All project keys associated with the priority scheme will only be returned if additional query parameter is provided expand=projectKeys.
 */
public suspend fun HttpClient.unassignPriorityScheme(
  schemeId: Long,
  projectKeyOrId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): PrioritySchemeBean? {
  val response = delete(urlString = """api/2/project/${projectKeyOrId}/priorityscheme/${schemeId}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<PrioritySchemeBean>()
  return output
}
