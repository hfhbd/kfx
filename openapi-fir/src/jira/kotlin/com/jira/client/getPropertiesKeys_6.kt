package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get keys of all properties for a user
 * Returns the keys of all properties for the user identified by the key or by the id.
 */
public suspend fun HttpClient.getPropertiesKeys_6(
  userKey: String? = null,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = `get`(urlString = """api/2/user/properties""") {
    parameter("userKey", userKey)
    parameter("username", username)
    builder()
  }
}
