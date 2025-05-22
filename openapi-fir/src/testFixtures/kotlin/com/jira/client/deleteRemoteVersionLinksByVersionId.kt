package com.jira.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import kotlin.String
import kotlin.Unit

/**
 * Delete all remote version links for version
 * Delete all remote version links for a given version ID.
 */
public suspend fun HttpClient.deleteRemoteVersionLinksByVersionId(versionId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}) {
  val response = delete(urlString = """api/2/version/${versionId}/remotelink""") {
    builder()
  }
}
