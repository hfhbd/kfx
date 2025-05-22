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
 * Store temporary avatar using multipart
 * Creates temporary avatar using multipart. The response is sent back as JSON stored in a textarea. This is because
 * the client uses remote iframing to submit avatars using multipart. So we must send them a valid HTML page back from
 * which the client parses the JSON.
 */
public suspend fun HttpClient.storeTemporaryAvatarUsingMultiPart_1(projectIdOrKey: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): AvatarCroppingBean? {
  val response = post(urlString = """api/2/project/${projectIdOrKey}/avatar/temporary""") {
    contentType(FormData)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<AvatarCroppingBean>()
  return output
}
