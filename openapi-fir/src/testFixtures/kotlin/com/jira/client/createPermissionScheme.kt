package com.jira.client

import com.jira.PermissionSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Create a new permission scheme
 * Create a new permission scheme. This method can create schemes with a defined permission set, or without.
 */
public suspend fun HttpClient.createPermissionScheme(
  input: PermissionSchemeBean,
  expand: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): PermissionSchemeBean {
  val response = post(urlString = """api/2/permissionscheme""") {
    parameter("expand", expand)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  val output = response.body<PermissionSchemeBean>()
  return output
}
