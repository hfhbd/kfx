package com.jira.client

import com.jira.FilterPermissionBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get all share permissions of filter
 * Returns all share permissions of the given filter
 *
 * @param id The filter id.
 */
public suspend fun HttpClient.getSharePermissions(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): FilterPermissionBean? {
  val response = `get`(urlString = """api/2/filter/${id}/permission""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<FilterPermissionBean>()
  return output
}
