package com.jira.client

import com.jira.PermissionGrantsBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Get all permission grants of a scheme
 * Returns all permission grants of the given permission scheme.
 */
public suspend fun HttpClient.getPermissionSchemeGrants(
  schemeId: Long,
  expand: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): PermissionGrantsBean? {
  val response = `get`(urlString = """api/2/permissionscheme/${schemeId}/permission""") {
    parameter("expand", expand)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<PermissionGrantsBean>()
  return output
}
