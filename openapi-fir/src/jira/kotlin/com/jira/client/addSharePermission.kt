package com.jira.client

import com.jira.FilterPermissionBean
import com.jira.SharePermissionInputBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Add share permissions to filter
 * Adds a share permissions to the given filter. Adding a global permission removes all previous permissions from the filter
 * @param id The filter id.
 */
public suspend fun HttpClient.addSharePermission(
  input: SharePermissionInputBean,
  id: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): FilterPermissionBean? {
  val response = post(urlString = """api/2/filter/${id}/permission""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<FilterPermissionBean>()
  return output
}
