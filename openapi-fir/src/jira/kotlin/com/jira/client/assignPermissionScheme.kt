package com.jira.client

import com.jira.IdBean
import com.jira.PermissionSchemeBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Assign permission scheme to project
 * Assigns a permission scheme with a project
 */
public suspend fun HttpClient.assignPermissionScheme(
  input: IdBean,
  projectKeyOrId: String,
  expand: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): PermissionSchemeBean? {
  val response = put(urlString = """api/2/project/${projectKeyOrId}/permissionscheme""") {
    parameter("expand", expand)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<PermissionSchemeBean>()
  return output
}
