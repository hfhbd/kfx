package com.jira.client

import com.jira.EntityPropertiesKeysBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get all properties keys for a dashboard item
 * Returns the keys of all properties for the dashboard item identified by the id.
 *
 * @param itemId The dashboard item from which keys will be returned.
 * @param dashboardId The dashboard id.
 */
public suspend fun HttpClient.getPropertiesKeys_3(
  itemId: String,
  dashboardId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): EntityPropertiesKeysBean? {
  val response = `get`(urlString = """api/2/dashboard/${dashboardId}/items/${itemId}/properties""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<EntityPropertiesKeysBean>()
  return output
}
