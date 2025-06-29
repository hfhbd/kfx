package com.jira.client

import com.jira.PasswordBean
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
 * Update user password
 * Modify user password.
 */
public suspend fun HttpClient.changeUserPassword(
  input: PasswordBean,
  key: String? = null,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = put(urlString = """api/2/user/password""") {
    parameter("key", key)
    parameter("username", username)
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
