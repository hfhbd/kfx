package com.jira.client

import com.jira.AttachmentMetaBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.Unit

/**
 * Get attachment capabilities
 * Returns the meta information for an attachments, specifically if they are enabled and the maximum upload size allowed.
 */
public suspend fun HttpClient.getAttachmentMeta(builder: suspend HttpRequestBuilder.() -> Unit = {}): AttachmentMetaBean {
  val response = `get`(urlString = """api/2/attachment/meta""") {
    builder()
  }
  val output = response.body<AttachmentMetaBean>()
  return output
}
