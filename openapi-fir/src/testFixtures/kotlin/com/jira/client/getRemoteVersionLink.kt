package com.jira.client

import com.jira.RemoteEntityLinkJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import kotlin.String
import kotlin.Unit

/**
 * Get specific remote version link
 * Returns the remote version link associated with the given version ID and global ID.
 */
public suspend fun HttpClient.getRemoteVersionLink(
  versionId: String,
  globalId: String,
  builder: suspend HttpRequestBuilder.() -> Unit = {},
): RemoteEntityLinkJsonBean? {
  val response = `get`(urlString = """api/2/version/${versionId}/remotelink/${globalId}""") {
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<RemoteEntityLinkJsonBean>()
  return output
}
