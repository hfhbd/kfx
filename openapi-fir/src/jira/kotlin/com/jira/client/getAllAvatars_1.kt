package com.jira.client

import com.jira.AvatarBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get all avatars for user
 * Returns all avatars which are visible for the currently logged in user.
 */
public suspend fun HttpClient.getAllAvatars_1(username: String? = null, builder: suspend HttpRequestBuilder.() -> Unit = {}): AvatarBean? {
  val response = `get`(urlString = """api/2/user/avatars""") {
    parameter("username", username)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<AvatarBean>()
  return output
}
