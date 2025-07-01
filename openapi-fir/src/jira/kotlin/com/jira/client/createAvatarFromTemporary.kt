package com.jira.client

import com.jira.AvatarCroppingBean
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Update avatar cropping
 * Updates the cropping instructions of the temporary avatar
 * @param type the avatar type
 */
public suspend fun HttpClient.createAvatarFromTemporary(
  input: AvatarCroppingBean,
  type: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = post(urlString = """api/2/avatar/${type}/temporaryCrop""") {
    contentType(Json)
    setBody(body = input)
    builder()
  }
}
