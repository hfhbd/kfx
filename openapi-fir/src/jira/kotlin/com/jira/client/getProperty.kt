package com.jira.client

import com.jira.EntityPropertiesKeysBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get a property from a board
 * Returns the value of the property with a given key from the board identified by the provided id. The user who retrieves the property is required to have permissions to view the board.
 */
public suspend fun HttpClient.getProperty(
  propertyKey: String,
  boardId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): EntityPropertiesKeysBean? {
  val response = `get`(urlString = """agile/1.0/board/${boardId}/properties/${propertyKey}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<EntityPropertiesKeysBean>()
  return output
}
