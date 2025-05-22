package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Text.Plain
import io.ktor.http.contentType
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Update or insert a scheme attribute
 * Updates or inserts the attribute for a permission scheme specified by permission scheme id. The attribute consists of the key and the value. The value will be converted to Boolean using Boolean#valueOf.
 */
public suspend fun HttpClient.setSchemeAttribute(
  input: String,
  permissionSchemeId: Long,
  key: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/permissionscheme/${permissionSchemeId}/attribute/${key}""") {
    contentType(Plain)
    setBody(body = input)
    builder()
  }
}
