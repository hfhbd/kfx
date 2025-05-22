package com.jira.client

import com.jira.AvatarCroppingBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlin.String
import kotlin.Unit

/**
 * Create temporary avatar
 * Creates temporary avatar
 *
 * @param type the avatar type
 * @param filename name of file being uploaded
 * @param size size of file
 */
public suspend fun HttpClient.storeTemporaryAvatar(
  type: String,
  filename: String? = null,
  size: String? = null,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): AvatarCroppingBean {
  val response = post(urlString = """api/2/avatar/${type}/temporary""") {
    parameter("filename", filename)
    parameter("size", size)
    builder()
  }
  val output = response.body<AvatarCroppingBean>()
  return output
}
