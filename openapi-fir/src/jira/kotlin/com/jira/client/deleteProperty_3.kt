package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete a property from an issue
 * Removes the property from the issue identified by the key or by the id.
 *
 * @param propertyKey The key of the property to remove
 * @param issueIdOrKey Issue id or key
 */
public suspend fun HttpClient.deleteProperty_3(
  propertyKey: String,
  issueIdOrKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/issue/${issueIdOrKey}/properties/${propertyKey}""") {
    builder()
  }
}
