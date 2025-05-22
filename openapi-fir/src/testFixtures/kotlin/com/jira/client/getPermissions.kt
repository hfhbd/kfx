package com.jira.client

import com.jira.PermissionsJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get permissions for the logged in user
 * Returns all permissions in the system and whether the currently logged in user has them. You can optionally provide a specific context to get permissions for (projectKey OR projectId OR issueKey OR issueId)
 *
 * @param issueId id of the issue to scope returned permissions for.
 * @param projectKey key of project to scope returned permissions for.
 * @param issueKey key of the issue to scope returned permissions for.
 * @param projectId id of project to scope returned permissions for.
 */
public suspend fun HttpClient.getPermissions(
  issueId: String? = null,
  projectKey: String? = null,
  issueKey: String? = null,
  projectId: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): PermissionsJsonBean? {
  val response = `get`(urlString = """api/2/mypermissions""") {
    parameter("issueId", issueId)
    parameter("projectKey", projectKey)
    parameter("issueKey", issueKey)
    parameter("projectId", projectId)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<PermissionsJsonBean>()
  return output
}
