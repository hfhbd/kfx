package com.jira.client

import com.jira.RemoteEntityLinksJsonBean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.`get`
import io.ktor.client.request.parameter
import kotlin.String
import kotlin.Unit

/**
 * Get remote version links by global ID
 * Returns the remote version links for a given global ID.
 */
public suspend fun HttpClient.getRemoteVersionLinks(globalId: String? = null, builder: suspend HttpRequestBuilder.() -> Unit = {}): RemoteEntityLinksJsonBean? {
  val response = `get`(urlString = """api/2/version/remotelink""") {
    parameter("globalId", globalId)
    builder()
  }
  if (response.status.value == 404) {
    return null
  }
  val output = response.body<RemoteEntityLinksJsonBean>()
  return output
}
