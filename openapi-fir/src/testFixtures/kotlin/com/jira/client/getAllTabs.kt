package com.jira.client

import com.jira.ScreenableTabBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.Long
import kotlin.String
import kotlin.Unit

/**
 * Get all tabs for a screen
 * Returns a list of all tabs for the given screen.
 */
public suspend fun HttpClient.getAllTabs(
  screenId: Long,
  projectKey: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): ScreenableTabBean {
  val response = `get`(urlString = """api/2/screens/${screenId}/tabs""") {
    parameter("projectKey", projectKey)
    builder()
  }
  val output = response.body<ScreenableTabBean>()
  return output
}
