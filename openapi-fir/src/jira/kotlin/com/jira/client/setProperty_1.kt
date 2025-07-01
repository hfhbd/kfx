package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Set a property on a comment
 * Sets the value of the specified comment's property.
 *
 * @param propertyKey the key of the comment's property. The maximum length of the key is 255 bytes.
 * @param commentId the comment on which the property will be set.
 */
public suspend fun HttpClient.setProperty_1(
  propertyKey: String,
  commentId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/comment/${commentId}/properties/${propertyKey}""") {
    contentType(Json)
    builder()
  }
}
