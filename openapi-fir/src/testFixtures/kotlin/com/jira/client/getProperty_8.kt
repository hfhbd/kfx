package com.jira.client

import com.jira.EntityPropertyBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get a property from a dashboard item
 * Returns the value of the property with a given key from the dashboard item identified by the id.
 *
 * @param propertyKey The key of the property to return.
 * @param itemId The dashboard item from which the property will be returned.
 * @param dashboardId The dashboard id.
 */
public suspend fun HttpClient.getProperty_8(
  propertyKey: String,
  itemId: String,
  dashboardId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): EntityPropertyBean? {
  val response = `get`(urlString = """api/2/dashboard/${dashboardId}/items/${itemId}/properties/${propertyKey}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<EntityPropertyBean>()
  return output
}
