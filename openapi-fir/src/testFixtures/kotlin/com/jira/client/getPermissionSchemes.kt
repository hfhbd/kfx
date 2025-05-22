package com.jira.client

import com.jira.PermissionSchemesBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get all permission schemes
 * Returns a list of all permission schemes. By default only shortened beans are returned. If you want to include permissions of all the schemes, then specify the permissions expand parameter. Permissions will be included also if you specify any other expand parameter.
 */
public suspend fun HttpClient.getPermissionSchemes(expand: String? = null, builder: suspend HttpRequestBuilder.() -> Unit = {}): PermissionSchemesBean? {
  val response = `get`(urlString = """api/2/permissionscheme""") {
    parameter("expand", expand)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<PermissionSchemesBean>()
  return output
}
