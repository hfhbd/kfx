package com.jira.client

import com.jira.EntityPropertiesKeysBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get all properties keys for a sprint
 * Returns the keys of all properties for the sprint identified by the id. The user who retrieves the property keys is required to have permissions to view the sprint.
 */
public suspend fun HttpClient.getPropertiesKeys_1(sprintId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): EntityPropertiesKeysBean? {
  val response = `get`(urlString = """agile/1.0/sprint/${sprintId}/properties""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<EntityPropertiesKeysBean>()
  return output
}
