package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete a property from a board
 * Removes the property from the board identified by the id. Ths user removing the property is required to have permissions to modify the board.
 */
public suspend fun HttpClient.deleteProperty(
  propertyKey: String,
  boardId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """agile/1.0/board/${boardId}/properties/${propertyKey}""") {
    builder()
  }
}
