package com.jira.client

import com.jira.PermissionGrantBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode.Companion.NotFound
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Get a permission grant by ID
 * Returns a permission grant identified by the given id.
 */
public suspend fun HttpClient.getPermissionSchemeGrant(
  permissionId: Long,
  schemeId: Long,
  expand: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): PermissionGrantBean? {
  val response = `get`(urlString = """api/2/permissionscheme/${schemeId}/permission/${permissionId}""") {
    parameter("expand", expand)
    builder()
  }
  when {
    response.status == NotFound -> {
      return null
    }
  }
  val output = response.body<PermissionGrantBean>()
  return output
}
