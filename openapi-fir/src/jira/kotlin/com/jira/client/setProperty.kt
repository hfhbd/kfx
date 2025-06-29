package com.jira.client

import com.jira.EntityPropertiesKeysBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import kotlin.String
import kotlin.Unit

/**
 * Update a board's property
 * Sets the value of the specified board's property. You can use this resource to store a custom data against the board identified by the id. The user who stores the data is required to have permissions to modify the board.
 */
public suspend fun HttpClient.setProperty(
  propertyKey: String,
  boardId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): EntityPropertiesKeysBean? {
  val response = put(urlString = """agile/1.0/board/${boardId}/properties/${propertyKey}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<EntityPropertiesKeysBean>()
  return output
}
