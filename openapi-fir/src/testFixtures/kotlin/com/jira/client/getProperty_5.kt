package com.jira.client

import com.jira.EntityPropertyBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get value of property from project
 * Returns the value of the property with a given key from the project identified by the key or by the id.
 */
public suspend fun HttpClient.getProperty_5(
  propertyKey: String,
  projectIdOrKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): EntityPropertyBean? {
  val response = `get`(urlString = """api/2/project/${projectIdOrKey}/properties/${propertyKey}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<EntityPropertyBean>()
  return output
}
