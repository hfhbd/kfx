package com.jira.client

import com.jira.PermissionGrantBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Create a permission grant in a scheme
 * Creates a permission grant in a permission scheme.
 */
public suspend fun HttpClient.createPermissionGrant(
  input: PermissionGrantBean,
  schemeId: Long,
  expand: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): PermissionGrantBean {
  val response = post(urlString = """api/2/permissionscheme/${schemeId}/permission""") {
    parameter("expand", expand)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<PermissionGrantBean>()
  return output
}
