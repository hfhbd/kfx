package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete specific remote version link
 * Delete a specific remote version link with the given version ID and global ID.
 */
public suspend fun HttpClient.deleteRemoteVersionLink(
  versionId: String,
  globalId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
) {
  val response = delete(urlString = """api/2/version/${versionId}/remotelink/${globalId}""") {
    builder()
  }
}
