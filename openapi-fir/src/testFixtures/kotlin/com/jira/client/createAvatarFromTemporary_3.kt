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
 * Creates avatar from temporary
 */
public suspend fun HttpClient.createAvatarFromTemporary_3(
  input: AvatarCroppingBean,
  type: String,
  owningObjectId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): AvatarBean? {
  val response = post(urlString = """api/2/universal_avatar/type/${type}/owner/${owningObjectId}/avatar""") {
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
