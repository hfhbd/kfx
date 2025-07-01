package com.jira.client

import com.jira.EntityPropertiesKeysBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get all properties keys for issue type
 * Returns the keys of all properties for the issue type identified by the id
 *
 * @param issueTypeId The issue type from which the keys will be returned.
 */
public suspend fun HttpClient.getPropertyKeys(issueTypeId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): EntityPropertiesKeysBean? {
  val response = `get`(urlString = """api/2/issuetype/${issueTypeId}/properties""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<EntityPropertiesKeysBean>()
  return output
}
