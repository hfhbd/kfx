package com.jira.client

import com.jira.PermissionSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode.Companion.NotFound
import kotlin.String
import kotlin.Unit

/**
 * Get assigned permission scheme
 * Gets a permission scheme assigned with a project
 */
public suspend fun HttpClient.getAssignedPermissionScheme(
  projectKeyOrId: String,
  expand: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): PermissionSchemeBean? {
  val response = `get`(urlString = """api/2/project/${projectKeyOrId}/permissionscheme""") {
    parameter("expand", expand)
    builder()
  }
  if (response.status == NotFound) {
    return null
  }
  val output = response.body<PermissionSchemeBean>()
  return output
}
