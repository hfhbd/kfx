package com.jira.client

import com.jira.UserWriteBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Update user details
 * Modify user. The 'value' fields present will override the existing value. Fields skipped in request will not be changed.
 */
public suspend fun HttpClient.updateUser_1(
  input: UserWriteBean,
  key: String? = null,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): UserWriteBean? {
  val response = put(urlString = """api/2/user""") {
    parameter("key", key)
    parameter("username", username)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<UserWriteBean>()
  return output
}
