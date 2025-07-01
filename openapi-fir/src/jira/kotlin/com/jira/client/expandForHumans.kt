package com.jira.client

import com.jira.HumanReadableArchive
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get human-readable attachment expansion
 * Tries to expand an attachment. Output is human-readable and subject to change.
 *
 * @param id the id of the attachment to expand.
 */
public suspend fun HttpClient.expandForHumans(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): HumanReadableArchive? {
  val response = `get`(urlString = """api/2/attachment/${id}/expand/human""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<HumanReadableArchive>()
  return output
}
