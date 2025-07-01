package com.jira.client

import com.jira.ProjectRoleActorsBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Long
import kotlin.Unit

/**
 * Get default actors for a role
 * Gets default actors for the given role.
 */
public suspend fun HttpClient.getProjectRoleActorsForRole(id: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}): ProjectRoleActorsBean? {
  val response = `get`(urlString = """api/2/role/${id}/actors""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ProjectRoleActorsBean>()
  return output
}
