package com.jira.client

import com.jira.Property
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get all advanced settings properties
 * Returns the properties that are displayed on the "General Configuration > Advanced Settings" page.
 */
public suspend fun HttpClient.getAdvancedSettings(builder: suspend HttpRequestBuilder.() -> Unit = {}): Property {
  val response = `get`(urlString = """api/2/application-properties/advanced-settings""") {
    builder()
  }
  val output = response.body<Property>()
  return output
}
