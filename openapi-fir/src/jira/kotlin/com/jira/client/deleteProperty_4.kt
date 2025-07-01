package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete specified issue type's property
 * Removes the property from the issue type identified by the id
 *
 * @param propertyKey The key of the property to remove.
 * @param issueTypeId The issue type from which the property will be removed.
 */
public suspend fun HttpClient.deleteProperty_4(
  propertyKey: String,
  issueTypeId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/issuetype/${issueTypeId}/properties/${propertyKey}""") {
    builder()
  }
}
