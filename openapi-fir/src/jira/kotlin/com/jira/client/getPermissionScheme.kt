package com.jira.client

import com.jira.PermissionSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Get a permission scheme by ID
 * Returns a permission scheme identified by the given id.
 */
public suspend fun HttpClient.getPermissionScheme(
  schemeId: Long,
  expand: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): PermissionSchemeBean? {
  val response = `get`(urlString = """api/2/permissionscheme/${schemeId}""") {
    parameter("expand", expand)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<PermissionSchemeBean>()
  return output
}
