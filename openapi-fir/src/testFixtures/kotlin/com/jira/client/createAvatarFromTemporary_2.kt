package com.jira.client

import com.jira.AvatarBean
import com.jira.AvatarCroppingBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Create avatar from temporary
 * Converts the temporary avatar into the final one. This is step 2/3 of changing an avatar for a project:
 * - Upload (store temporary avatar)
 * - Crop (create avatar from temporary)
 * - Update (update project avatar)
 */
public suspend fun HttpClient.createAvatarFromTemporary_2(
  input: AvatarCroppingBean,
  projectIdOrKey: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): AvatarBean? {
  val response = post(urlString = """api/2/project/${projectIdOrKey}/avatar""") {
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
