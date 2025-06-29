package com.jira.client

import com.jira.EntityPropertyBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get value of specified issue type's property
 * Returns the value of the property with a given key from the issue type identified by the id
 *
 * @param propertyKey The key of the property to return.
 * @param issueTypeId The issue type from which the property will be returned.
 */
public suspend fun HttpClient.getProperty_7(
  propertyKey: String,
  issueTypeId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): EntityPropertyBean? {
  val response = `get`(urlString = """api/2/issuetype/${issueTypeId}/properties/${propertyKey}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<EntityPropertyBean>()
  return output
}
