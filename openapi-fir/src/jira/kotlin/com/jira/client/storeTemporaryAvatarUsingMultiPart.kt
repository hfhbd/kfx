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
 * Create temporary avatar using multipart for issue type
 * Creates temporary avatar using multipart. The response is sent back as JSON stored in a textarea. This is because
 * the client uses remote iframing to submit avatars using multipart. So we must send them a valid HTML page back from
 * which the client parses the JSON from.
 * Creating a temporary avatar is part of a 3-step process in uploading a new
 * avatar for an issue type: upload, crop, confirm. This endpoint allows you to use a multipart upload
 * instead of sending the image directly as the request body.
 * You *must* use "avatar" as the name of the upload parameter:
 * curl -c cookiejar.txt -X POST -u admin:admin -H "X-Atlassian-Token: no-check" \
 *   -F "avatar=@mynewavatar.png;type=image/png" \
 *   'http://localhost:8090/jira/rest/api/2/issuetype/1/avatar/temporary'
 *
 * @param id The issue type id.
 */
public suspend fun HttpClient.storeTemporaryAvatarUsingMultiPart(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): AvatarCroppingBean? {
  val response = post(urlString = """api/2/issuetype/${id}/avatar/temporary""") {
    contentType(FormData)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<AvatarCroppingBean>()
  return output
}
