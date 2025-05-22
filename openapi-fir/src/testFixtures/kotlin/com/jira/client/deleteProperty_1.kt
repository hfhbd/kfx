package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete a property from a dashboard item
 * Removes the property from the dashboard item identified by the key or by the id.
 *
 * @param propertyKey The key of the property to remove.
 * @param itemId The dashboard item from which the property will be removed.
 * @param dashboardId The dashboard id.
 */
public suspend fun HttpClient.deleteProperty_1(
  propertyKey: String,
  itemId: String,
  dashboardId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/dashboard/${dashboardId}/items/${itemId}/properties/${propertyKey}""") {
    builder()
  }
}
