package com.jira.client

import com.jira.EntityPropertiesKeysBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get properties keys of a comment
 * Returns the keys of all properties for the comment identified by the key or by the id.
 *
 * @param commentId the comment from which keys will be returned.
 */
public suspend fun HttpClient.getPropertiesKeys_4(commentId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): EntityPropertiesKeysBean? {
  val response = `get`(urlString = """api/2/comment/${commentId}/properties""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<EntityPropertiesKeysBean>()
  return output
}
