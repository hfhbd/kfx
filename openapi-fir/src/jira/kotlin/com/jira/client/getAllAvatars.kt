package com.jira.client

import com.jira.AvatarBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get all avatars for a project
 * Returns all avatars which are visible for the currently logged in user. The avatars are grouped into system and custom.
 */
public suspend fun HttpClient.getAllAvatars(projectIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): AvatarBean? {
  val response = `get`(urlString = """api/2/project/${projectIdOrKey}/avatars""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<AvatarBean>()
  return output
}
