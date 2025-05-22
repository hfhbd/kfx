package com.jira.client

import com.jira.EntityPropertyBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get a property for a sprint
 * Returns the value of the property with a given key from the sprint identified by the provided id. The user who retrieves the property is required to have permissions to view the sprint.
 */
public suspend fun HttpClient.getProperty_1(
  propertyKey: String,
  sprintId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): EntityPropertyBean? {
  val response = `get`(urlString = """agile/1.0/sprint/${sprintId}/properties/${propertyKey}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<EntityPropertyBean>()
  return output
}
