package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import kotlin.String
import kotlin.Unit

/**
 * Update a sprint's property
 * Sets the value of the specified sprint's property. You can use this resource to store a custom data against the sprint identified by the id. The user who stores the data is required to have permissions to modify the sprint.
 */
public suspend fun HttpClient.setProperty_6(
  propertyKey: String,
  sprintId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """agile/1.0/sprint/${sprintId}/properties/${propertyKey}""") {
    builder()
  }
}
