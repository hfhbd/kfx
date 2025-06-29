package com.jira.client

import com.jira.AvatarCroppingBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.http.ContentType.MultiPart.FormData
import io.ktor.http.contentType
import kotlin.String
import kotlin.Unit

/**
 * Create temporary avatar using multipart upload
 * Creates temporary avatar
 */
public suspend fun HttpClient.storeTemporaryAvatarUsingMultiPart_2(
  type: String,
  owningObjectId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): AvatarCroppingBean? {
  val response = post(urlString = """api/2/universal_avatar/type/${type}/owner/${owningObjectId}/temp""") {
    contentType(FormData)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<AvatarCroppingBean>()
  return output
}
