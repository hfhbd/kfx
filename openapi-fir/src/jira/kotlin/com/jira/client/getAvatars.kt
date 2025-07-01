package com.jira.client

import com.jira.AvatarBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get all avatars for a type and owner
 * Returns a list of all avatars
 */
public suspend fun HttpClient.getAvatars(
  type: String,
  owningObjectId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): AvatarBean? {
  val response = `get`(urlString = """api/2/universal_avatar/type/${type}/owner/${owningObjectId}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<AvatarBean>()
  return output
}
