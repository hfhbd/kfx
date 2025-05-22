package com.jira.client

import com.jira.ScreenableFieldBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Long
import kotlin.Unit

/**
 * Get available fields for screen
 * Gets available fields for screen. i.e ones that haven't already been added.
 */
public suspend fun HttpClient.getFieldsToAdd(screenId: Long, builder: suspend HttpRequestBuilder.() -> Unit = {}): ScreenableFieldBean {
  val response = `get`(urlString = """api/2/screens/${screenId}/availableFields""") {
    builder()
  }
  val output = response.body<ScreenableFieldBean>()
  return output
}
