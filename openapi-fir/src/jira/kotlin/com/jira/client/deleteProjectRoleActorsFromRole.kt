package com.jira.client

import com.jira.ProjectRoleActorsBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Removes default actor from a role
 * Removes default actor from the given role.
 */
public suspend fun HttpClient.deleteProjectRoleActorsFromRole(
  id: Long,
  user: String? = null,
  group: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ProjectRoleActorsBean? {
  val response = delete(urlString = """api/2/role/${id}/actors""") {
    parameter("user", user)
    parameter("group", group)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<ProjectRoleActorsBean>()
  return output
}
