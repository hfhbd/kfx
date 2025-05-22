package com.jira.client

import com.jira.EntityPropertiesKeysBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get all properties keys for a board
 * Returns the keys of all properties for the board identified by the id. The user who retrieves the property keys is required to have permissions to view the board.
 */
public suspend fun HttpClient.getPropertiesKeys(boardId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): EntityPropertiesKeysBean? {
  val response = `get`(urlString = """agile/1.0/board/${boardId}/properties""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<EntityPropertiesKeysBean>()
  return output
}
