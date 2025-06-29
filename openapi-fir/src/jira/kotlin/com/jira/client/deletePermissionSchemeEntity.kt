package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.Long
import kotlin.Unit

/**
 * Delete a permission grant from a scheme
 * Deletes a permission grant from a permission scheme.
 */
public suspend fun HttpClient.deletePermissionSchemeEntity(
  permissionId: Long,
  schemeId: Long,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/permissionscheme/${schemeId}/permission/${permissionId}""") {
    builder()
  }
}
