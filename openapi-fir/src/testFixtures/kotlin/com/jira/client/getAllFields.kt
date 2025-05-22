package com.jira.client

import com.jira.ScreenableFieldBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Get all fields for a tab
 * Gets all fields for a given tab.
 */
public suspend fun HttpClient.getAllFields(
  tabId: Long,
  screenId: Long,
  projectKey: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ScreenableFieldBean {
  val response = `get`(urlString = """api/2/screens/${screenId}/tabs/${tabId}/fields""") {
    parameter("projectKey", projectKey)
    builder()
  }
  val output = response.body<ScreenableFieldBean>()
  return output
}
