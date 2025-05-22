package com.jira.client

import com.jira.RemoteEntityLinksJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get remote version links by version ID
 * Returns the remote version links associated with the given version ID.
 */
public suspend fun HttpClient.getRemoteVersionLinksByVersionId(versionId: String, builder: suspend HttpRequestBuilder.() -> Unit = {}): RemoteEntityLinksJsonBean? {
  val response = `get`(urlString = """api/2/version/${versionId}/remotelink""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<RemoteEntityLinksJsonBean>()
  return output
}
