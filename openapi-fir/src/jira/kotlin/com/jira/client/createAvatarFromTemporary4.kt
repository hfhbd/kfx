package com.jira.client

import com.jira.AvatarBean
import com.jira.AvatarCroppingBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Create avatar from temporary
 * Converts temporary avatar into a real avatar
 */
public suspend fun HttpClient.createAvatarFromTemporary4(
  input: AvatarCroppingBean,
  username: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): AvatarBean? {
  val response = post(urlString = """api/2/user/avatar""") {
    parameter("username", username)
    contentType(Json)
    setBody(input)
    builder()
  }
  when {
    response.status == NotFound -> {
      return null
    }
  }
  val output = response.body<AvatarBean>()
  return output
}
