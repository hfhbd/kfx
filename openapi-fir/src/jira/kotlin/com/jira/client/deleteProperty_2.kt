package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete a property from a comment
 * Removes the property from the comment identified by the key or by the id. Ths user removing the property is required to have permissions to administer the comment.
 *
 * @param propertyKey the key of the property to remove.
 * @param commentId the comment from which the property will be removed.
 */
public suspend fun HttpClient.deleteProperty_2(
  propertyKey: String,
  commentId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/comment/${commentId}/properties/${propertyKey}""") {
    builder()
  }
}
