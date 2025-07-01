package com.jira.client

import com.jira.EntityPropertiesKeysBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get keys of all properties for an issue
 * Returns the keys of all properties for the issue identified by the key or by the id.
 *
 * @param issueIdOrKey Issue id or key
 */
public suspend fun HttpClient.getPropertiesKeys_2(issueIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): EntityPropertiesKeysBean? {
  val response = `get`(urlString = """api/2/issue/${issueIdOrKey}/properties""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<EntityPropertiesKeysBean>()
  return output
}
