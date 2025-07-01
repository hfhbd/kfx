package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get the value of a specified user's property
 * Returns the value of the property with a given key from the user identified by the key or by the id.
 */
public suspend fun HttpClient.getProperty_6(
  propertyKey: String,
  userKey: String? = null,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = `get`(urlString = """api/2/user/properties/${propertyKey}""") {
    parameter("userKey", userKey)
    parameter("username", username)
    builder()
  }
}
