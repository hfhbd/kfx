package com.jira.client

import com.jira.AttachmentBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get the meta-data for an attachment, including the URI of the actual attached file
 * Returns the meta-data for an attachment, including the URI of the actual attached file.
 *
 * @param id id of the attachment to view
 */
public suspend fun HttpClient.getAttachment(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): AttachmentBean? {
  val response = `get`(urlString = """api/2/attachment/${id}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<AttachmentBean>()
  return output
}
