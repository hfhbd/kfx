package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Update base URL for Jira instance
 * Sets the base URL that is configured for this Jira instance.
 */
public suspend fun HttpClient.setBaseURL(input: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = put(urlString = """api/2/settings/baseUrl""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
