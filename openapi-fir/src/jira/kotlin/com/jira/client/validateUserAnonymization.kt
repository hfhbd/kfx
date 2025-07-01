package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get validation for user anonymization
 * Validates user anonymization process.
 */
public suspend fun HttpClient.validateUserAnonymization(
  expand: String? = null,
  userKey: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = `get`(urlString = """api/2/user/anonymization""") {
    parameter("expand", expand)
    parameter("userKey", userKey)
    builder()
  }
}
