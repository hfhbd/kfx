package com.jira.client

import com.jira.ProjectRoleBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Long
import kotlin.Unit

/**
 * Get a specific project role
 * Get a specific ProjectRole available in Jira.
 */
public suspend fun HttpClient.getProjectRolesById(id: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}): ProjectRoleBean? {
  val response = `get`(urlString = """api/2/role/${id}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ProjectRoleBean>()
  return output
}
