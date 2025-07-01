package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import kotlin.String
import kotlin.Unit

/**
 * Add field to default screen
 * Moves field on the given tab.
 */
public suspend fun HttpClient.addFieldToDefaultScreen(fieldId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = post(urlString = """api/2/screens/addToDefault/${fieldId}""") {
    builder()
  }
}
