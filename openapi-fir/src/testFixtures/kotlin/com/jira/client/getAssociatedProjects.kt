package com.jira.client

import com.jira.ProjectBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get all of the associated projects for specified scheme
 * For the specified issue type scheme, returns all of the associated projects
 *
 * @param schemeId Id of the issue type scheme whose projects we're accessing
 */
public suspend fun HttpClient.getAssociatedProjects(
  schemeId: String,
  expand: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ProjectBean? {
  val response = `get`(urlString = """api/2/issuetypescheme/${schemeId}/associations""") {
    parameter("expand", expand)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ProjectBean>()
  return output
}
