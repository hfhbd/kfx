package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Delete a specified user's property
 * Removes the property from the user identified by the key or by the id. The user who removes the property is required to have permissions to administer the user.
 */
public suspend fun HttpClient.deleteProperty_6(
  propertyKey: String,
  userKey: String? = null,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/user/properties/${propertyKey}""") {
    parameter("userKey", userKey)
    parameter("username", username)
    builder()
  }
}
