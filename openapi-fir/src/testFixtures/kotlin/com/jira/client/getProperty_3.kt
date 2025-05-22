package com.jira.client

import com.jira.EntityPropertyBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get the value of a specific property from an issue
 * Returns the value of the property with a given key from the issue identified by the key or by the id.
 *
 * @param propertyKey The key of the property to return
 * @param issueIdOrKey Issue id or key
 */
public suspend fun HttpClient.getProperty_3(
  propertyKey: String,
  issueIdOrKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): EntityPropertyBean? {
  val response = `get`(urlString = """api/2/issue/${issueIdOrKey}/properties/${propertyKey}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<EntityPropertyBean>()
  return output
}
