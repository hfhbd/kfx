package com.jira.client

import com.jira.AvatarBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get all system avatars
 * Returns all system avatars of the given type.
 *
 * @param type the avatar type
 */
public suspend fun HttpClient.getAllSystemAvatars(type: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): AvatarBean {
  val response = `get`(urlString = """api/2/avatar/${type}/system""") {
    builder()
  }
  val output = response.body<AvatarBean>()
  return output
}
