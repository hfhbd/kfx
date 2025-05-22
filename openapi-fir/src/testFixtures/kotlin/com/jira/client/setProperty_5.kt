package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Set the value of a specified user's property
 * Sets the value of the specified user's property.
 * You can use this resource to store a custom data against the user identified by the key or by the id. The user
 * who stores the data is required to have permissions to administer the user.
 */
public suspend fun HttpClient.setProperty_5(
  input: String,
  propertyKey: String,
  userKey: String? = null,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/user/properties/${propertyKey}""") {
    parameter("userKey", userKey)
    parameter("username", username)
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
