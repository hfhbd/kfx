package com.jira.client

import com.jira.AttachmentArchiveImpl
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get raw attachment expansion
 * Tries to expand an attachment. Output is raw and should be backwards-compatible through the course of time.
 *
 * @param id the id of the attachment to expand.
 */
public suspend fun HttpClient.expandForMachines(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): AttachmentArchiveImpl? {
  val response = `get`(urlString = """api/2/attachment/${id}/expand/raw""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<AttachmentArchiveImpl>()
  return output
}
