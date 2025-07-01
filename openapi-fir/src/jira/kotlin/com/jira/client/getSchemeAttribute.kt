package com.jira.client

import com.jira.PermissionSchemeAttributeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Get scheme attribute by key
 * Returns the attribute for a permission scheme specified by permission scheme id and attribute key.
 */
public suspend fun HttpClient.getSchemeAttribute(
  permissionSchemeId: Long,
  attributeKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): PermissionSchemeAttributeBean? {
  val response = `get`(urlString = """api/2/permissionscheme/${permissionSchemeId}/attribute/${attributeKey}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<PermissionSchemeAttributeBean>()
  return output
}
