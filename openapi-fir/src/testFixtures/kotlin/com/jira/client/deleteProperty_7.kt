package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete a sprint's property
 * Removes the property from the sprint identified by the id. Ths user removing the property is required to have permissions to modify the sprint.
 */
public suspend fun HttpClient.deleteProperty_7(
  propertyKey: String,
  sprintId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """agile/1.0/sprint/${sprintId}/properties/${propertyKey}""") {
    builder()
  }
}
