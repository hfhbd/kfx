package com.jira.client

import com.jira.FilterPermissionBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get a single share permission of filter
 * Returns a single share permission of the given filter
 *
 * @param permissionId The permission id.
 * @param id The filter id.
 */
public suspend fun HttpClient.getSharePermission(
  permissionId: String,
  id: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): FilterPermissionBean? {
  val response = `get`(urlString = """api/2/filter/${id}/permission/${permissionId}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<FilterPermissionBean>()
  return output
}
