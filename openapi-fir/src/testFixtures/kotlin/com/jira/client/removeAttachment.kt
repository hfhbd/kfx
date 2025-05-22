package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete an attachment from an issue
 * Remove an attachment from an issue.
 *
 * @param id id of the attachment to remove
 */
public suspend fun HttpClient.removeAttachment(id: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """api/2/attachment/${id}""") {
    builder()
  }
}
