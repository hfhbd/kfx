package com.jira.client

import com.jira.AvatarBean
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
 * Update user avatar
 * Updates the avatar for the user.
 */
public suspend fun HttpClient.updateUserAvatar_1(
  input: AvatarBean,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): AvatarBean? {
  val response = put(urlString = """api/2/user/avatar""") {
    parameter("username", username)
    contentType(Json)
    setBody(body = input)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<AvatarBean>()
  return output
}
