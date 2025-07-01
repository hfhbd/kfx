package com.jira.client

import com.jira.ProjectRoleBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Get details for a project role
 * Returns the details for a given project role in a project.
 */
public suspend fun HttpClient.getProjectRole(
  projectIdOrKey: String,
  id: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ProjectRoleBean? {
  val response = `get`(urlString = """api/2/project/${projectIdOrKey}/role/${id}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ProjectRoleBean>()
  return output
}
