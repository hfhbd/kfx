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
 * Get a project by ID or key
 * Returns a full representation of a project in JSON format. All project keys associated with the project will only be returned if <code>expand=projectKeys</code>.
 */
public suspend fun HttpClient.getProject(
  projectIdOrKey: String,
  expand: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ProjectBean? {
  val response = `get`(urlString = """api/2/project/${projectIdOrKey}""") {
    parameter("expand", expand)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ProjectBean>()
  return output
}
