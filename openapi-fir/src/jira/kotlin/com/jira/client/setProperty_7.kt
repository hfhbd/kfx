package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import kotlin.String
import kotlin.Unit

/**
 * Set a property on a dashboard item
 * Sets the value of the property with a given key on the dashboard item identified by the id.
 *
 * @param propertyKey The key of the dashboard item's property. The maximum length of the key is 255 bytes.
 * @param itemId The dashboard item on which the property will be set.
 * @param dashboardId The dashboard id.
 */
public suspend fun HttpClient.setProperty_7(
  propertyKey: String,
  itemId: String,
  dashboardId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/dashboard/${dashboardId}/items/${itemId}/properties/${propertyKey}""") {
    builder()
  }
}
